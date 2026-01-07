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
import com.xworkz.techroute_userservice.repository.UserRepository;
import com.xworkz.techroute_userservice.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void registerUser_success() {
        RegisterRequest request = new RegisterRequest("shriharsha", "test@example.com", "password");
        UserEntity entity = new UserEntity();
        entity.setId(UUID.randomUUID());
        entity.setUsername("shriharsha");
        entity.setEmail("test@example.com");
        entity.setPasswordHash("encoded");

        when(userRepository.existsByUsername(request.getUsername())).thenReturn(false);
        when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encoded");
        when(userRepository.save(any(UserEntity.class))).thenReturn(entity);

        UserResponse response = userService.registerUser(request);

        assertThat(response.getUsername()).isEqualTo("shriharsha");
        assertThat(response.getEmail()).isEqualTo("test@example.com");
    }

    @Test
    void registerUser_usernameExists_throwsException() {
        RegisterRequest request = new RegisterRequest("shriharsha", "test@example.com", "password");
        when(userRepository.existsByUsername(request.getUsername())).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> userService.registerUser(request));
    }

    @Test
    void registerUser_emailExists_throwsException() {
        RegisterRequest request = new RegisterRequest("shriharsha", "test@example.com", "password");
        when(userRepository.existsByEmail(request.getEmail())).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> userService.registerUser(request));
    }

    @Test
    void login_success() {
        LoginRequest request = new LoginRequest("test@example.com", "password");
        UserEntity entity = new UserEntity();
        entity.setId(UUID.randomUUID());
        entity.setEmail("test@example.com");
        entity.setPasswordHash("encoded");
        entity.setRole(Role.CUSTOMER);

        when(userRepository.findByEmail(request.getEmail())).thenReturn(entity);
        when(passwordEncoder.matches(request.getPassword(), entity.getPasswordHash())).thenReturn(true);
        when(jwtUtil.generateToken(anyString(), anyString())).thenReturn("jwt-token");
        when(jwtUtil.getExpirationMs()).thenReturn(3600000L);

        AuthResponse response = userService.login(request);

        assertThat(response.getAccessToken()).isEqualTo("jwt-token");
        assertThat(response.getExpiresIn()).isEqualTo(3600);
    }

    @Test
    void login_emailNotFound_throwsException() {
        LoginRequest request = new LoginRequest("missing@example.com", "password");
        when(userRepository.findByEmail(request.getEmail())).thenReturn(null);

        assertThrows(EmailNotExistException.class, () -> userService.login(request));
    }

    @Test
    void login_passwordMismatch_throwsException() {
        LoginRequest request = new LoginRequest("test@example.com", "wrong");
        UserEntity entity = new UserEntity();
        entity.setPasswordHash("encoded");

        when(userRepository.findByEmail(request.getEmail())).thenReturn(entity);
        when(passwordEncoder.matches(request.getPassword(), entity.getPasswordHash())).thenReturn(false);

        assertThrows(PasswordMissMatchException.class, () -> userService.login(request));
    }

    @Test
    void updateUserRole_success() {
        UUID id = UUID.randomUUID();
        UserEntity entity = new UserEntity();
        entity.setId(id);
        entity.setRole(Role.CUSTOMER);

        when(userRepository.findById(id)).thenReturn(Optional.of(entity));
        when(userRepository.save(entity)).thenReturn(entity);

        UserResponse response = userService.updateUserRole(id, Role.ADMIN);

        assertThat(response.getRole()).isEqualTo("ADMIN");
    }

    @Test
    void updateUserRole_userNotFound_throwsException() {
        UUID id = UUID.randomUUID();
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.updateUserRole(id, Role.ADMIN));
    }

    @Test
    void getAllUsers_success() {
        UserEntity entity = new UserEntity();
        entity.setUsername("shriharsha");
        entity.setEmail("test@example.com");

        when(userRepository.findAll()).thenReturn(List.of(entity));

        List<UserResponse> responses = userService.getAllUsers();

        assertThat(responses.size()==1);
        assertThat(responses.get(0).getUsername()).isEqualTo("shriharsha");
    }
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//       UUID  userId = UUID.randomUUID();
//        UserEntity userEntity = new UserEntity();
//        userEntity.setId(userId);
//        userEntity.setStatus(Status.INACTIVE);
//    }

    @Test
    void updateUserStatus_success() {
        UUID  userId = UUID.randomUUID();
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        userEntity.setStatus(Status.INACTIVE);
        Status newStatus = Status.ACTIVE;
        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));
        when(userRepository.save(any(UserEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UserResponse response = userService.updateUserStatus(userId, newStatus);

        assertNotNull(response);
        assertEquals("ACTIVE", response.getStatus());
        verify(userRepository).findById(userId);
        verify(userRepository).save(userEntity);
    }
    @Test
    void updateUserStatus_userNotFound() {
        UUID  userId = UUID.randomUUID();
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        userEntity.setStatus(Status.INACTIVE);
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> userService.updateUserStatus(userId, Status.ACTIVE));

        assertEquals("User not found", exception.getMessage());
        verify(userRepository).findById(userId);
        verify(userRepository, never()).save(any(UserEntity.class));
    }


}
