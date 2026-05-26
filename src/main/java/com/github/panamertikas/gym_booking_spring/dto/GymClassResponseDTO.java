package com.github.panamertikas.gym_booking_spring.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GymClassResponseDTO {

    private Long id;
    private String className;
    private String trainer;
    private int currentCapacity;
    private int maxCapacity;
}
