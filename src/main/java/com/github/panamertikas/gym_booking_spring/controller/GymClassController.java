package com.github.panamertikas.gym_booking_spring.controller;

import com.github.panamertikas.gym_booking_spring.dto.GymClassMapper;
import com.github.panamertikas.gym_booking_spring.dto.GymClassRequestDTO;
import com.github.panamertikas.gym_booking_spring.dto.GymClassResponseDTO;
import com.github.panamertikas.gym_booking_spring.model.GymClass;
import com.github.panamertikas.gym_booking_spring.service.GymClassService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@RestController
public class GymClassController {

    @Autowired
    private GymClassService gymClassService;

    @PostMapping("/gym_classes")
    public GymClassResponseDTO save(@Valid @RequestBody GymClassRequestDTO dto) {
        GymClass gymClass = GymClassMapper.toEntity(dto);
        gymClassService.save(gymClass);
        return GymClassMapper.toResponseDTO(gymClass);
    }

    @DeleteMapping("/gym_classes/{id}")
    public void delete (@PathVariable Long id) {
        gymClassService.findById(id).ifPresent(gymClassService::delete);
    }


    @GetMapping("/gym_classes/{id}")
    public GymClassResponseDTO findById(@PathVariable Long id) {
        return gymClassService.findById(id)
                .map(GymClassMapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Gym class not found!"));
    }

    @GetMapping("/gym_classes")
    public List<GymClassResponseDTO> findAll() {
        return gymClassService.findAll()
                .stream()
                .map(GymClassMapper::toResponseDTO)
                .toList();
    }
}
