package com.xworkz.techroute_userservice.config;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuditorAwareImplTest {

    private final AuditorAwareImpl auditorAware = new AuditorAwareImpl();

    @AfterEach
    void clearContext() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void whenNoAuthentication_thenSystemReturned() {
        SecurityContextHolder.clearContext();
        Optional<String> auditor = auditorAware.getCurrentAuditor();
        assertEquals("SYSTEM", auditor.get());
    }

    @Test
    void whenAuthenticationNotAuthenticated_thenSystemReturned() {
        TestingAuthenticationToken auth = new TestingAuthenticationToken("user", "password");
        auth.setAuthenticated(false);
        SecurityContextHolder.getContext().setAuthentication(auth);

        Optional<String> auditor = auditorAware.getCurrentAuditor();
        assertEquals("SYSTEM", auditor.get());
    }

    @Test
    void whenPrincipalIsUserDetails_thenUsernameReturned() {
        UserDetails userDetails = User.withUsername("testuser")
                .password("password")
                .roles("USER")
                .build();

        TestingAuthenticationToken auth = new TestingAuthenticationToken(userDetails, "password");
        auth.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(auth);

        Optional<String> auditor = auditorAware.getCurrentAuditor();
        assertEquals("testuser", auditor.get());
    }

    @Test
    void whenPrincipalIsString_thenStringReturned() {
        TestingAuthenticationToken auth = new TestingAuthenticationToken("stringUser", "password");
        auth.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(auth);

        Optional<String> auditor = auditorAware.getCurrentAuditor();
        assertEquals("stringUser", auditor.get());
    }

    @Test
    void whenPrincipalIsNull_thenSystemReturned() {
        TestingAuthenticationToken auth = new TestingAuthenticationToken(null, "password");
        auth.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(auth);

        Optional<String> auditor = auditorAware.getCurrentAuditor();
        assertEquals("SYSTEM", auditor.get());
    }
}

