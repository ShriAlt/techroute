package com.xworkz.techroute_product_service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import java.util.Optional;

@Configuration
@EnableMongoAuditing
public class AuditorAwareImpl implements AuditorAware<String > {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("SYSTEM");

    }
}
