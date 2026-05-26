package com.github.panamertikas.gym_booking_spring.service;

import com.github.panamertikas.gym_booking_spring.model.Booking;
import com.github.panamertikas.gym_booking_spring.model.GymClass;
import com.github.panamertikas.gym_booking_spring.model.Member;
import com.github.panamertikas.gym_booking_spring.repository.BookingRepository;
import com.github.panamertikas.gym_booking_spring.repository.GymClassRepository;
import com.github.panamertikas.gym_booking_spring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private GymClassRepository gymClassRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BookingRepository bookingRepository;

    public void save(Booking booking) {
        GymClass gymClass = gymClassRepository.findById(booking.getGymClass().getId())
                .orElseThrow(() -> new RuntimeException("GymClass not found!"));
        Member member = memberRepository.findById(booking.getMember().getId())
                .orElseThrow(() -> new RuntimeException("Member not found!"));

        if (bookingRepository.existsByMemberAndGymClass(member, gymClass)) {
            throw new RuntimeException("Member " + member.getMail() + " already has a booking for " + gymClass.getClassName());
        }

        if (gymClass.getCurrentCapacity() >= gymClass.getMaxCapacity()) {
            throw new RuntimeException("GymClass " + gymClass.getClassName() + " is full!");
        }

        gymClass.setCurrentCapacity(gymClass.getCurrentCapacity() + 1);
        booking.setGymClass(gymClass);
        booking.setMember(member);
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
