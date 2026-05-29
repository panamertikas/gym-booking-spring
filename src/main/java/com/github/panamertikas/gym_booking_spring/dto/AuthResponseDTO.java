package com.github.panamertikas.gym_booking_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DTO for authentication response containing the JWT token.
 */
@Getter
@AllArgsConstructor
public class AuthResponseDTO {
    private String token;
}