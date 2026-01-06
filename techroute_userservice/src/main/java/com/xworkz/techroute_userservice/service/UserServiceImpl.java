package com.xworkz.techroute_userservice.service;

import com.xworkz.techroute_userservice.dto.RegisterRequest;
import com.xworkz.techroute_userservice.dto.UserResponse;
import com.xworkz.techroute_userservice.enity.UserEntity;
import com.xworkz.techroute_userservice.enums.Role;
import com.xworkz.techroute_userservice.enums.Status;
import com.xworkz.techroute_userservice.mapper.UserMapper;
import com.xworkz.techroute_userservice.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

//    @Override
//    public UserResponse registerUser(RegisterRequest registerRequest) {
//
//        UserEntity userEntity = UserMapper.INSTANCE.registerRequestToUserEntity(registerRequest);
//        userEntity.setPasswordHash(passwordEncoder.encode(registerRequest.getPassword()));
//        userEntity.setRole(Role.CUSTOMER);
//        userEntity.setStatus(Status.ACTIVE);
//        UserEntity save = userRepository.save(userEntity);
//        return null;
//    }


    @Override
    public UserResponse registerUser(RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        UserEntity userEntity = UserMapper.INSTANCE.registerRequestToUserEntity(registerRequest);
        userEntity.setPasswordHash(passwordEncoder.encode(registerRequest.getPassword()));
        userEntity.setRole(Role.CUSTOMER);
        userEntity.setStatus(Status.ACTIVE);
        UserEntity savedUser = userRepository.save(userEntity);
        return UserMapper.INSTANCE.userEntityToUserResponse(savedUser);
    }
}