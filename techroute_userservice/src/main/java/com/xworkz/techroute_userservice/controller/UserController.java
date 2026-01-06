package com.xworkz.techroute_userservice.controller;

import com.xworkz.techroute_userservice.dto.AuthResponse;
import com.xworkz.techroute_userservice.dto.LoginRequest;
import com.xworkz.techroute_userservice.dto.RegisterRequest;
import com.xworkz.techroute_userservice.dto.UserResponse;

import com.xworkz.techroute_userservice.enity.UserEntity;
import com.xworkz.techroute_userservice.mapper.UserMapper;
import com.xworkz.techroute_userservice.repository.UserRepository;
import com.xworkz.techroute_userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    public UserController(UserService userService , UserRepository userRepository) {
        this.userService = userService;
        this.userRepository=userRepository;
    }


    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request) {
        UserResponse response = userService.registerUser(request);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        AuthResponse response = userService.login(loginRequest);
         return ResponseEntity.ok(response);
    }
    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser(Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        UserEntity user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new RuntimeException("User not found"));
        UserResponse response = UserMapper.INSTANCE.userEntityToUserResponse(user);
        return ResponseEntity.ok(response);
    }

}


