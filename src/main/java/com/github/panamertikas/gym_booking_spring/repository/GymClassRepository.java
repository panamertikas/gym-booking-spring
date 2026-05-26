package com.github.panamertikas.gym_booking_spring.repository;

import com.github.panamertikas.gym_booking_spring.model.GymClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GymClassRepository extends JpaRepository<GymClass, Long> {

    boolean existsByClassNameAndTrainer(String className, String trainer);
}
