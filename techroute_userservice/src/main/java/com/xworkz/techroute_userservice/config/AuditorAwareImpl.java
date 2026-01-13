package com.xworkz.techroute_userservice.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;
// this is for audit table will update audit table automatically
@Component
public class AuditorAwareImpl implements AuditorAware<String > {

    @Override
    public Optional<String> getCurrentAuditor() {
        // Return logged-in username, or "SYSTEM" if none
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.of("SYSTEM"); // fallback if no user is logged in
        }

        Object principal = authentication.getPrincipal();
        if (principal == null) {
            return Optional.of("SYSTEM"); // avoid NPE
        }

        if (principal instanceof UserDetails userDetails) {
            return Optional.of(userDetails.getUsername());
        }

        return Optional.of(principal.toString());


    }
}
