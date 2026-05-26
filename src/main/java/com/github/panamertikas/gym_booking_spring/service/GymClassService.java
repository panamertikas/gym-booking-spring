package com.github.panamertikas.gym_booking_spring.service;

import com.github.panamertikas.gym_booking_spring.model.GymClass;
import com.github.panamertikas.gym_booking_spring.repository.GymClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GymClassService {

    @Autowired
    private GymClassRepository gymClassRepository;

    public void save (GymClass gymClass) {
        gymClassRepository.save(gymClass);
    }

    public void delete (GymClass gymClass) {
        gymClassRepository.delete(gymClass);
    }

    public Optional<GymClass> findById(Long id) {
        return  gymClassRepository.findById(id);
    }

    public List<GymClass> findAll() {
        return gymClassRepository.findAll();
    }
}
