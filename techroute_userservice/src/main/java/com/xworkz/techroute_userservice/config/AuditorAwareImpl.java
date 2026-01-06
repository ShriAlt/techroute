package com.xworkz.techroute_userservice.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;
// this is for audit table will update audit table automatically
@Component
public class AuditorAwareImpl implements AuditorAware<String > {

    @Override
    public Optional<String> getCurrentAuditor() {
        // Return logged-in username, or "SYSTEM" if none
        return Optional.of("SYSTEM");
    }

}
