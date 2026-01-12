package com.xworkz.techroute_userservice.config;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AuditorAwareImplTest {

    private final AuditorAwareImpl auditorAware = new AuditorAwareImpl();

    @Test
    void getCurrentAuditor_shouldReturnSystem() {
        Optional<String> auditor = auditorAware.getCurrentAuditor();

        assertTrue(auditor.isPresent(), "Auditor should be present");
        assertTrue(auditor.get().equals("SYSTEM"), "Auditor should be SYSTEM");
    }
}
