package com.github.panamertikas.gym_booking_spring.controller;

import com.github.panamertikas.gym_booking_spring.model.Booking;
import com.github.panamertikas.gym_booking_spring.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/bookings")
    public void save(@RequestBody Booking booking) {
        bookingService.save(booking);
    }

    @DeleteMapping("/bookings/{id}")
    public void delete (@PathVariable Long id) {
        bookingService.findById(id).ifPresent(bookingService::delete);
    }

    @GetMapping("/bookings/{id}")
    public Optional<Booking> findById(@PathVariable Long id) {
        return bookingService.findById(id);
    }

    @GetMapping("/bookings")
    public List<Booking> findAll() {
        return bookingService.findAll();
    }
}
