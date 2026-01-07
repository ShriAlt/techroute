package com.xworkz.techroute_userservice.service;

import com.xworkz.techroute_userservice.dto.AuthResponse;
import com.xworkz.techroute_userservice.dto.LoginRequest;
import com.xworkz.techroute_userservice.dto.RegisterRequest;
import com.xworkz.techroute_userservice.dto.UserResponse;
import com.xworkz.techroute_userservice.enity.UserEntity;
import com.xworkz.techroute_userservice.enums.Role;
import com.xworkz.techroute_userservice.enums.Status;
import com.xworkz.techroute_userservice.exception.EmailNotExistException;
import com.xworkz.techroute_userservice.exception.PasswordMissMatchException;
import com.xworkz.techroute_userservice.mapper.UserMapper;
import com.xworkz.techroute_userservice.repository.UserRepository;
import com.xworkz.techroute_userservice.util.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }


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

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        UserEntity user = userRepository.findByEmail(loginRequest.getEmail());
        if (user == null){
            throw  new EmailNotExistException("email do not exist");
        }
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPasswordHash())) {
            throw new PasswordMissMatchException("Invalid credentials");
        }
        String token = jwtUtil.generateToken(user.getId().toString(), user.getRole().name());
        AuthResponse response = new AuthResponse();
        response.setAccessToken(token);
        response.setExpiresIn(jwtUtil.getExpirationMs() / 1000);
        return response;
    }

    @Override
    public UserResponse updateUserRole(UUID id, Role role) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setRole(Role.valueOf(role.toString().toUpperCase()));
        UserEntity updated = userRepository.save(user);
        return UserMapper.INSTANCE.userEntityToUserResponse(updated);
    }

    @Override
    public UserResponse updateUserStatus(UUID id, Status status) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setStatus(Status.valueOf(status.toString().toUpperCase()));
        UserEntity updated = userRepository.save(user);
        return UserMapper.INSTANCE.userEntityToUserResponse(updated);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper.INSTANCE::userEntityToUserResponse)
                .toList();
    }

}
