package com.xworkz.techroute_userservice.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = AppConfig.class)
class AppConfigSpringTest {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Test
    void bCryptPasswordEncoder_shouldBeAvailableAsBean() {
        String rawPassword = "mySecret123";
        String encodedPassword = encoder.encode(rawPassword);

        assertTrue(encoder.matches(rawPassword, encodedPassword));
    }
}
