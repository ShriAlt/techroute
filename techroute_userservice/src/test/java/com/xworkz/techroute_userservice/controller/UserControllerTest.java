package com.xworkz.techroute_userservice.controller;

import com.xworkz.techroute_userservice.dto.AuthResponse;
import com.xworkz.techroute_userservice.dto.LoginRequest;
import com.xworkz.techroute_userservice.dto.RegisterRequest;
import com.xworkz.techroute_userservice.dto.UserResponse;
import com.xworkz.techroute_userservice.enity.UserEntity;
import com.xworkz.techroute_userservice.enums.Role;
import com.xworkz.techroute_userservice.enums.Status;
import com.xworkz.techroute_userservice.mapper.UserMapper;
import com.xworkz.techroute_userservice.repository.UserRepository;
import com.xworkz.techroute_userservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void register_success() {
        RegisterRequest request = new RegisterRequest();
        request.setUsername("shriharsha");
        request.setEmail("test@example.com");
        request.setPassword("password");

        UserResponse responseDto = new UserResponse("shriharsha", "test@example.com", Role.CUSTOMER, Status.ACTIVE);

        when(userService.registerUser(request)).thenReturn(responseDto);

        ResponseEntity<UserResponse> response = userController.register(request);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("shriharsha", response.getBody().getUsername());
        assertEquals("test@example.com", response.getBody().getEmail());
    }

    @Test
    void login_success() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("password");

        AuthResponse authResponse = new AuthResponse("jwt-token");

        when(userService.login(loginRequest)).thenReturn(authResponse);

        ResponseEntity<AuthResponse> response = userController.login(loginRequest);

        assertEquals(200, response.getStatusCode().value());
        assert response.getBody() != null;
        assertEquals("jwt-token", response.getBody().getAccessToken());
    }

    @Test
    void getCurrentUser_success() {
        UUID userId = UUID.randomUUID();
        when(authentication.getPrincipal()).thenReturn(userId.toString());

        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        userEntity.setUsername("shriharsha");
        userEntity.setEmail("test@example.com");
        userEntity.setRole(Role.CUSTOMER);
        userEntity.setStatus(Status.ACTIVE);
        userEntity.setCreatedAt(LocalDateTime.now());
        userEntity.setUpdatedAt(LocalDateTime.now());

        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));

        UserResponse mappedResponse = UserMapper.INSTANCE.userEntityToUserResponse(userEntity);

        ResponseEntity<UserResponse> response = userController.getCurrentUser(authentication);

        assertEquals(200, response.getStatusCode().value());
        assert response.getBody() != null;
        assertEquals(mappedResponse.getUsername(), response.getBody().getUsername());
        assertEquals(mappedResponse.getEmail(), response.getBody().getEmail());
    }
}
