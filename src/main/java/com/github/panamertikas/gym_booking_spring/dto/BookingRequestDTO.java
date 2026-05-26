package com.github.panamertikas.gym_booking_spring.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BookingRequestDTO {

    private String date;
    private String time;
    private Long gymClassId;
    private Long memberId;
}
