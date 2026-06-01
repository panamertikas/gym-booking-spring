package com.github.panamertikas.gym_booking_spring.controller;

import com.github.panamertikas.gym_booking_spring.dto.BookingMapper;
import com.github.panamertikas.gym_booking_spring.dto.BookingRequestDTO;
import com.github.panamertikas.gym_booking_spring.dto.BookingResponseDTO;
import com.github.panamertikas.gym_booking_spring.model.Booking;
import com.github.panamertikas.gym_booking_spring.model.GymClass;
import com.github.panamertikas.gym_booking_spring.model.User;
import com.github.panamertikas.gym_booking_spring.repository.BookingRepository;
import com.github.panamertikas.gym_booking_spring.repository.GymClassRepository;
import com.github.panamertikas.gym_booking_spring.repository.UserRepository;
import com.github.panamertikas.gym_booking_spring.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private GymClassRepository gymClassRepository;

    @PostMapping("/bookings")
    public BookingResponseDTO save(@RequestBody BookingRequestDTO dto) {
        Booking booking = BookingMapper.toEntity(dto);
        bookingService.save(booking, dto.getGymClassId(), dto.getMemberId());
        return BookingMapper.toResponseDTO(booking);
    }

    @DeleteMapping("/bookings/{id}")
    public void delete(@PathVariable Long id) {
        bookingService.findById(id).ifPresent(bookingService::delete);
    }

    @GetMapping("/bookings/{id}")
    public BookingResponseDTO findById(@PathVariable Long id) {
        return bookingService.findById(id)
                .map(BookingMapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Booking not found!"));
    }

    @GetMapping("/bookings")
    public List<BookingResponseDTO> findAll() {
        return bookingService.findAll()
                .stream()
                .map(BookingMapper::toResponseDTO)
                .toList();
    }

    @GetMapping("/bookings/my")
    public List<BookingResponseDTO> getMyBookings(Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        if (user.getMember() == null) {
            return List.of();
        }

        return bookingRepository.findByMember(user.getMember())
                .stream()
                .map(BookingMapper::toResponseDTO)
                .toList();
    }

    @GetMapping("/bookings/availability/{gymClassId}/{date}")
    public boolean checkAvailability(@PathVariable Long gymClassId, @PathVariable String date) {
        GymClass gymClass = gymClassRepository.findById(gymClassId)
                .orElseThrow(() -> new RuntimeException("GymClass not found!"));
        long bookings = bookingRepository.countByGymClassAndDate(gymClass, date);
        return bookings < gymClass.getMaxCapacity();
    }
}