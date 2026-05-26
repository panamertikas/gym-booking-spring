package com.github.panamertikas.gym_booking_spring.service;

import com.github.panamertikas.gym_booking_spring.model.Booking;
import com.github.panamertikas.gym_booking_spring.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public void add(Booking booking) {
        bookingRepository.save(booking);
    }

    public void delete(Booking booking) {
        bookingRepository.delete(booking);
    }

    public Optional<Booking> findById(Long id) {
        return bookingRepository.findById(id);
    }

    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }
}
