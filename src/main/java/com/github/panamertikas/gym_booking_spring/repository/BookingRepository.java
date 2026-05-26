package com.github.panamertikas.gym_booking_spring.repository;

import com.github.panamertikas.gym_booking_spring.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}
