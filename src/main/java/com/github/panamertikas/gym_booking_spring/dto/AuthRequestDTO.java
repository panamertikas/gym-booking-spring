package com.github.panamertikas.gym_booking_spring.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO for authentication requests (login and register).
 */

@Getter
@Setter
public class AuthRequestDTO {
    private String username;
    private String password;
}