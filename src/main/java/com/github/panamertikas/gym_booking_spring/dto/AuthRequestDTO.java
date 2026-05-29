package com.github.panamertikas.gym_booking_spring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequestDTO {
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private int age;
}