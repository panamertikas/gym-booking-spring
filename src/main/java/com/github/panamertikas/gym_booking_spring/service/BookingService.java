package com.github.panamertikas.gym_booking_spring.service;

import com.github.panamertikas.gym_booking_spring.model.Booking;
import com.github.panamertikas.gym_booking_spring.model.GymClass;
import com.github.panamertikas.gym_booking_spring.model.Member;
import com.github.panamertikas.gym_booking_spring.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public void save(Booking booking) {
        GymClass gymClass = booking.getGymClass();
        Member member = booking.getMember();

        if (bookingRepository.existsByMemberAndGymClass(member, gymClass)) {
            throw new RuntimeException("Member " + member.getFirstname() + " already has a booking for " + gymClass.getClassname());
        }

        if (gymClass.getCurrentCapacity() >= gymClass.getMaxCapacity()) {
            throw new RuntimeException("GymClass " + gymClass.getClassname() + " is full!");
        }

        gymClass.setCurrentCapacity(gymClass.getCurrentCapacity() + 1);
        bookingRepository.save(booking);
    }

    public void delete(Booking booking) {
        GymClass gymClass = booking.getGymClass();
        gymClass.setCurrentCapacity(gymClass.getCurrentCapacity() - 1);
        bookingRepository.delete(booking);
    }

    public Optional<Booking> findById(Long id) {
        return bookingRepository.findById(id);
    }

    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }


}
