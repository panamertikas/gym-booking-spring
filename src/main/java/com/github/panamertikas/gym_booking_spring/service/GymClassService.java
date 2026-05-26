package com.github.panamertikas.gym_booking_spring.service;

import com.github.panamertikas.gym_booking_spring.model.GymClass;
import com.github.panamertikas.gym_booking_spring.repository.BookingRepository;
import com.github.panamertikas.gym_booking_spring.repository.GymClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GymClassService {

    @Autowired
    private GymClassRepository gymClassRepository;

    @Autowired
    private BookingRepository bookingRepository;

    public void save(GymClass gymClass) {
        if (gymClassRepository.existsByClassNameAndTrainer(gymClass.getClassName(), gymClass.getTrainer())) {
            throw new RuntimeException("GymClass " + gymClass.getClassName() + " with trainer " + gymClass.getTrainer() + " already exists!");
        }
        gymClassRepository.save(gymClass);
    }

    public void delete(GymClass gymClass) {
        if (bookingRepository.existsByGymClass(gymClass)) {
            throw new RuntimeException("GymClass " + gymClass.getClassName() + " has active bookings and cannot be deleted!");
        }
        gymClassRepository.delete(gymClass);
    }

    public Optional<GymClass> findById(Long id) {
        return  gymClassRepository.findById(id);
    }

    public List<GymClass> findAll() {
        return gymClassRepository.findAll();
    }
}
