package com.github.panamertikas.gym_booking_spring.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "gym_classes")
public class GymClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "class_name", nullable = false)
    private String classname;
    @Column(nullable = false)
    private String trainer;
    @Column(name = "current_capacity")
    private int currentCapacity;
    @Column(name = "max_capacity")
    private int maxCapacity;
}
