package com.github.panamertikas.gym_booking_spring.dto;

import com.github.panamertikas.gym_booking_spring.model.MembershipType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberResponseDTO {

    private Long id;
    private String firstname;
    private String lastname;
    private int age;
    private String mail;
    private MembershipType membershipType;
}