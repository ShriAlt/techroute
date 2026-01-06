package com.xworkz.techroute_userservice.service;

import com.xworkz.techroute_userservice.dto.AuthResponse;
import com.xworkz.techroute_userservice.dto.LoginRequest;
import com.xworkz.techroute_userservice.dto.RegisterRequest;
import com.xworkz.techroute_userservice.dto.UserResponse;
import com.xworkz.techroute_userservice.enums.Role;
import com.xworkz.techroute_userservice.enums.Status;


import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponse registerUser(RegisterRequest registerRequest);

    AuthResponse login(LoginRequest loginRequest);

    UserResponse updateUserRole(UUID id, Role role);

    UserResponse updateUserStatus(UUID id, Status status);

    List<UserResponse> getAllUsers();
}
