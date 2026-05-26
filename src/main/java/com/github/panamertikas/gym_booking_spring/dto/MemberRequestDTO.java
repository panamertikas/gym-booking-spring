package com.github.panamertikas.gym_booking_spring.dto;

import com.github.panamertikas.gym_booking_spring.model.MembershipType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRequestDTO {

    @NotBlank(message = "Firstname cannot be blank")
    @Pattern(regexp = "^[a-zA-Zα-ωΑ-Ω\\s]+$", message = "Firstname must contain only letters")
    private String firstname;

    @NotBlank(message = "Lastname cannot be blank")
    @Pattern(regexp = "^[a-zA-Zα-ωΑ-Ω\\s]+$", message = "Lastname must contain only letters")
    private String lastname;

    private int age;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email must be valid")
    private String mail;

    private MembershipType membershipType;
}