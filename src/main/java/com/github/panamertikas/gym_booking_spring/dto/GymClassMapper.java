package com.github.panamertikas.gym_booking_spring.dto;

import com.github.panamertikas.gym_booking_spring.model.GymClass;

public class GymClassMapper {

    public static GymClass  toEntity(GymClassRequestDTO dto) {
        GymClass gymClass = new GymClass();
        gymClass.setClassName(dto.getClassName());
        gymClass.setTrainer(dto.getTrainer());
        gymClass.setCurrentCapacity(0);
        gymClass.setMaxCapacity(dto.getMaxCapacity());
        return gymClass;
    }

    public static GymClassResponseDTO toResponseDTO (GymClass gymClass) {

        GymClassResponseDTO dto = new GymClassResponseDTO();
        dto.setId(gymClass.getId());
        dto.setClassName(gymClass.getClassName());
        dto.setTrainer(gymClass.getTrainer());
        dto.setCurrentCapacity(gymClass.getCurrentCapacity());
        dto.setMaxCapacity(gymClass.getMaxCapacity());
        return dto;
    }
}
