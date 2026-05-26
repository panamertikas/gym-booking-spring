package com.github.panamertikas.gym_booking_spring.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "bookings")
public class Booking implements IBookable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private String time;

    @ManyToOne
    @JoinColumn(name = "gym_class_id", nullable = false)
    private GymClass gymClass;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;




    @Override
    public String getDate() {
        return date;
    }

    @Override
    public String getTime() {
        return time;
    }

    @Override
    public GymClass getGymClass() {
        return gymClass;
    }
}
