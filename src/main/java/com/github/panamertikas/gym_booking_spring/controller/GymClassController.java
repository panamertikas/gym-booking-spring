package com.github.panamertikas.gym_booking_spring.controller;

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
    public void save(@RequestBody @Valid GymClass gymClass) {
        gymClassService.save(gymClass);
    }

    @DeleteMapping("/gym_classes/{id}")
    public void delete (@PathVariable Long id) {
        gymClassService.findById(id).ifPresent(gymClassService::delete);
    }


    @GetMapping("/gym_classes/{id}")
    public Optional<GymClass> findById(@PathVariable Long id) {
       return gymClassService.findById(id);
    }

    @GetMapping("/gym_classes")
    public List<GymClass> findAll() {
        return gymClassService.findAll();
    }
}
