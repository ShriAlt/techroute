package com.xworkz.techroute_userservice.dto;

import lombok.Data;

@Data
public class AuthResponse {

    private String accessToken;
    private String refreshToken; // optional
    private long expiresIn;
}

