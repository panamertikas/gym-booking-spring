package com.github.panamertikas.gym_booking_spring.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GymClassRequestDTO {

    @NotBlank(message = "Class name can not be blank")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Class name must contain only letters")
    private String className;

    @NotBlank(message = "Trainer can not be blank")
    @Pattern(regexp = "^[a-zA-Zα-ωΑ-Ω\\s]+$", message = "Trainer must contain only letters")
    private String trainer;


    @Min(value = 1, message = "Max capacity must be at least 1")
    private int maxCapacity;
}
