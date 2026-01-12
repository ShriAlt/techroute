package com.xworkz.techroute_userservice.config;

import com.xworkz.techroute_userservice.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest(classes = SecurityConfig.class)
class SecurityConfigTest {

    @Autowired
    private SecurityFilterChain filterChain;
    @Test
    void securityFilterChain_shouldBeCreated() {
        assertNotNull(filterChain, "SecurityFilterChain should not be null");
    }

}
