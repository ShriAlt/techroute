package com.xworkz.techroute_product_service.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {


    private final Key key ;

    public JwtUtil(@Value("${spring.jwt.secret}") String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes()); }

//    private static final long expirationMs = 3600000; // 1 hour

//    public String generateToken(String userId, String role) {
//        Date now = new Date();
//        Date expiry = new Date(now.getTime() + expirationMs);
//
//        return Jwts.builder()
//                .setSubject(userId)                // sub → user ID/email
//                .claim("role", role)               // role → CUSTOMER/ADMIN
//                .setIssuedAt(now)                  // iat
//                .setExpiration(expiry)             // exp
//                .signWith(key, SignatureAlgorithm.HS256)
//                .compact();
//    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUserId(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    public String getRole(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().get("role", String.class);
    }

//    public long getExpirationMs() {
//        return expirationMs;
//    }
}


