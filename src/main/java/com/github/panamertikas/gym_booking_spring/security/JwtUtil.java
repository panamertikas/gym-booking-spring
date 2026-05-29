package com.github.panamertikas.gym_booking_spring.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET = "mySecretKey12345678901234567890123456789012";
    private static final long EXPIRATION = 1000 * 60 * 60 * 24; // 24 hours

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }


    /**
     * Generates a JWT token for the given username.
     * @param username the username to generate the token for
     * @return the generated JWT token
     */
    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(getKey())
                .compact();
    }


    /**
     * Extracts the username from the given JWT token.
     * @param token the JWT token
     * @return the username
     */
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }


    /**
     * Validates the given JWT token.
     * @param token the JWT token
     * @return true if valid, false otherwise
     */

    public boolean isTokenValid(String token) {
        try {
            return getClaims(token).getExpiration().after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}