package com.github.panamertikas.gym_booking_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DTO for authentication response containing the JWT token and role.
 */
@Getter
@AllArgsConstructor
public class AuthResponseDTO {
    private String token;
    private String role;
}