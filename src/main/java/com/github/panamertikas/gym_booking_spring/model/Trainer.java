package com.github.panamertikas.gym_booking_spring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "trainers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Trainer extends Person{

    private String speciality;
}
