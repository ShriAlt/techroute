package com.xworkz.techroute_userservice.service;

import com.xworkz.techroute_userservice.enity.UserEntity;
import com.xworkz.techroute_userservice.enums.Role;
import com.xworkz.techroute_userservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MyUserDetailServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private MyUserDetailService myUserDetailService;

    @Test
    void loadUserByUsername_success() {
        UserEntity user = new UserEntity();
        user.setId(UUID.randomUUID());
        user.setUsername("shriharsha");
        user.setPasswordHash("encodedPassword");
        user.setRole(Role.CUSTOMER);

        when(userRepository.findByUsername("shriharsha")).thenReturn(user);

        UserDetails userDetails = myUserDetailService.loadUserByUsername("shriharsha");
        assertThat(userDetails.getUsername()).isEqualTo("shriharsha");
        assertThat(userDetails.getPassword()).isEqualTo("encodedPassword");
        assertThat(userDetails.getAuthorities())
                .extracting("authority")
                .containsExactly("ROLE_CUSTOMER");
    }

    @Test
    void loadUserByUsername_userNotFound_throwsException() {
        when(userRepository.findByUsername("missingUser")).thenReturn(null);
        assertThrows(UsernameNotFoundException.class,
                () -> myUserDetailService.loadUserByUsername("missingUser"));
    }
}
