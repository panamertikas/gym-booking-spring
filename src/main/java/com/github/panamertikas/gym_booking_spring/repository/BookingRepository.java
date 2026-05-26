package com.github.panamertikas.gym_booking_spring.repository;

import com.github.panamertikas.gym_booking_spring.model.Booking;
import com.github.panamertikas.gym_booking_spring.model.GymClass;
import com.github.panamertikas.gym_booking_spring.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>
{
    boolean existsByMemberAndGymClass(Member member, GymClass gymClass);

    boolean existsByMember(Member member);

    boolean existsByGymClass(GymClass gymClass);
}
