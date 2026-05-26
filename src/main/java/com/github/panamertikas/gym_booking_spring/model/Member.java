package com.github.panamertikas.gym_booking_spring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "members")
public class Member extends Person{



    @NotBlank(message = "Email can not be blank.")
    @Email(message = "Email must be valid.")
    private String mail;

    @Enumerated(EnumType.STRING)
    private MembershipType membershipType;
}
