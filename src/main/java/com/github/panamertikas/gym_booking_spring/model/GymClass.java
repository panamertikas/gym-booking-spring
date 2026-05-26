package com.github.panamertikas.gym_booking_spring.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "Class name cannot be blank")
    private String classname;

    @Column(nullable = false)
    @NotBlank(message = "Trainer cannot be blank")
    private String trainer;

    @Column(name = "current_capacity")
    private int currentCapacity;

    @Column(name = "max_capacity")
    @Min(value = 1, message = "Max capacity must be at least 1")
    private int maxCapacity;

}
