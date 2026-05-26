package com.github.panamertikas.gym_booking_spring.model;

import jakarta.persistence.*;
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



    private String mail;

    @Enumerated(EnumType.STRING)
    private MembershipType membershipType;
}
