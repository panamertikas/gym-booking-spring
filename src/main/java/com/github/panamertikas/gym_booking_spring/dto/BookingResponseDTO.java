package com.github.panamertikas.gym_booking_spring.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BookingResponseDTO {

    private Long id;
    private String date;
    private String time;
    private GymClassResponseDTO gymClass;
    private MemberResponseDTO member;
}
