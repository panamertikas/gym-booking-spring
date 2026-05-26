package com.github.panamertikas.gym_booking_spring.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name can not be null.")
    @Pattern(regexp = "^[a-zA-Zα-ωΑ-Ω\\s]+$", message = "Firstname must contain only letters.")
    private String firstname;

    @NotBlank(message = "Lastname can not be blank.")
    @Pattern(regexp = "^[a-zA-Zα-ωΑ-Ω\\s]+$", message = "Lastname must contain only letters.")
    private String lastname;

    private int age;

}
