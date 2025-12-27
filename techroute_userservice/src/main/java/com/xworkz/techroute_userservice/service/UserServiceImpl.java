package com.xworkz.techroute_userservice.service;

import com.xworkz.techroute_userservice.dto.RegisterRequest;
import com.xworkz.techroute_userservice.dto.UserResponse;
import com.xworkz.techroute_userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public UserResponse registerUser(RegisterRequest registerRequest) {
        
        return null;
    }
}
