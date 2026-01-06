package com.xworkz.techroute_userservice.controller;

import com.xworkz.techroute_userservice.dto.AuthResponse;
import com.xworkz.techroute_userservice.dto.LoginRequest;
import com.xworkz.techroute_userservice.dto.RegisterRequest;
import com.xworkz.techroute_userservice.dto.UserResponse;

import com.xworkz.techroute_userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request) {
        UserResponse response = userService.registerUser(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse response =null;
        return ResponseEntity.ok(response);
    }


//    @GetMapping("/me")
//    public ResponseEntity<UserResponse> getCurrentUser() {
//        UserResponse response = userService.getCurrentUser();
//        return ResponseEntity.ok(response);
//    }
}

