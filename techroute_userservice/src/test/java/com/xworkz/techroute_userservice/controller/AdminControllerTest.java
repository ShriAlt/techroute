package com.xworkz.techroute_userservice.controller;

import com.xworkz.techroute_userservice.dto.RoleUpdateRequest;
import com.xworkz.techroute_userservice.dto.StatusUpdateRequest;
import com.xworkz.techroute_userservice.dto.UserResponse;
import com.xworkz.techroute_userservice.enums.Role;
import com.xworkz.techroute_userservice.enums.Status;
import com.xworkz.techroute_userservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdminControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private AdminController adminController;


    @Test
    void listAllUsers_success() {

        UserResponse user = new UserResponse("shriharsha", "test@example.com", Role.CUSTOMER, Status.ACTIVE);

        when(userService.getAllUsers()).thenReturn(List.of(user));

        ResponseEntity<List<UserResponse>> response = adminController.listAllUsers();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(1, response.getBody().size());
        assertEquals("shriharsha", response.getBody().get(0).getUsername());
    }

    @Test
    void updateUserRole_success() {
        UUID userId = UUID.randomUUID();
        UserResponse updatedUser = new UserResponse("shriharsha", "test@example.com", Role.ADMIN, Status.ACTIVE);

        when(userService.updateUserRole(userId, Role.ADMIN)).thenReturn(updatedUser);

        RoleUpdateRequest request = new RoleUpdateRequest();
        request.setRole(Role.ADMIN);

        ResponseEntity<UserResponse> response = adminController.updateUserRole(userId, request);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("ADMIN", response.getBody().getRole());
    }

    @Test
    void updateUserStatus_success() {
        UUID userId = UUID.randomUUID();
        UserResponse updatedUser = new UserResponse("shriharsha", "test@example.com", Role.CUSTOMER, Status.INACTIVE);

        when(userService.updateUserStatus(userId, Status.INACTIVE)).thenReturn(updatedUser);

        StatusUpdateRequest request = new StatusUpdateRequest();
        request.setStatus(Status.INACTIVE);

        ResponseEntity<UserResponse> response = adminController.updateUserStatus(userId, request);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("INACTIVE", response.getBody().getStatus());
    }
}
