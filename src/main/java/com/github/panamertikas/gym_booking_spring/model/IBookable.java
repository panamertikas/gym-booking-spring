package com.github.panamertikas.gym_booking_spring.model;

public interface IBookable {
    String getDate();
    String getTime();
    GymClass getGymClass(); // κεφαλαίο C
}