package com.xworkz.techroute_userservice.util;

import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class JwtUtilTest {

    private JwtUtil jwtUtil;

//    @BeforeEach
//    void setup() {
//        jwtUtil = new JwtUtil();
//    }

    @Test
    void generateToken_containsUserIdAndRole() {
        String token = jwtUtil.generateToken("user123", "CUSTOMER");

        assertThat(jwtUtil.validateToken(token)).isTrue();
        assertThat(jwtUtil.getUserId(token)).isEqualTo("user123");
        assertThat(jwtUtil.getRole(token)).isEqualTo("CUSTOMER");
    }

    @Test
    void validateToken_invalidToken_returnsFalse() {
        String invalidToken = "this-is-not-a-jwt";
        assertThat(jwtUtil.validateToken(invalidToken)).isFalse();
    }

    @Test
    void getUserId_returnsCorrectSubject() {
        String token = jwtUtil.generateToken("user456", "ADMIN");
        assertThat(jwtUtil.getUserId(token)).isEqualTo("user456");
    }

    @Test
    void getRole_returnsCorrectRole() {
        String token = jwtUtil.generateToken("user789", "ADMIN");
        assertThat(jwtUtil.getRole(token)).isEqualTo("ADMIN");
    }

    @Test
    void getExpirationMs_returnsOneHour() {
        assertThat(jwtUtil.getExpirationMs()).isEqualTo(3600000L);
    }
}
