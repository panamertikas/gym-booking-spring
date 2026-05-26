package com.github.panamertikas.gym_booking_spring.dto;

import com.github.panamertikas.gym_booking_spring.model.Booking;

public class BookingMapper {

    public static Booking toEntity(BookingRequestDTO dto) {
        Booking booking = new Booking();
        booking.setTime(dto.getTime());
        booking.setDate(dto.getDate());
        return booking;
    }

    public static BookingResponseDTO toResponseDTO(Booking booking) {

        BookingResponseDTO dto = new BookingResponseDTO();
        dto.setId(booking.getId());
        dto.setDate(booking.getDate());
        dto.setTime(booking.getTime());
        dto.setGymClass(GymClassMapper.toResponseDTO(booking.getGymClass()));
        dto.setMember(MemberMapper.toResponseDTO(booking.getMember()));
        return dto;
    }
}
