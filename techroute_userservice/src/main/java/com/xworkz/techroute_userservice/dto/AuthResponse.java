package com.xworkz.techroute_userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private String accessToken;
    private String refreshToken;
    private long expiresIn;

    public AuthResponse(String accessToken) {
        this.accessToken= accessToken;

    }
}

