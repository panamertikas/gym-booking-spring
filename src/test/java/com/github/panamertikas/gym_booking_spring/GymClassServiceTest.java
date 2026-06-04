package com.github.panamertikas.gym_booking_spring;

import com.github.panamertikas.gym_booking_spring.model.GymClass;
import com.github.panamertikas.gym_booking_spring.repository.BookingRepository;
import com.github.panamertikas.gym_booking_spring.repository.GymClassRepository;
import com.github.panamertikas.gym_booking_spring.service.GymClassService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for GymClassService.
 * Uses Mockito to mock dependencies and test business logic in isolation.
 */
@ExtendWith(MockitoExtension.class)
class GymClassServiceTest {

    @Mock
    private GymClassRepository gymClassRepository;

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private GymClassService gymClassService;

    /**
     * Tests that saving a gym class with duplicate name and trainer throws RuntimeException.
     */
    @Test
    @DisplayName("Should throw exception when class with same name and trainer already exists")
    void save_shouldThrowException_whenClassAlreadyExists() {
        // Arrange
        GymClass gymClass = new GymClass();
        gymClass.setClassName("Yoga");
        gymClass.setTrainer("Maria");

        when(gymClassRepository.existsByClassNameAndTrainer("Yoga", "Maria")).thenReturn(true);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            gymClassService.save(gymClass);
        });

        assertEquals("GymClass Yoga with trainer Maria already exists!", exception.getMessage());
    }

    /**
     * Tests that saving a new gym class saves successfully.
     */
    @Test
    @DisplayName("Should save gym class when it does not already exist")
    void save_shouldSaveGymClass_whenClassDoesNotExist() {
        // Arrange
        GymClass gymClass = new GymClass();
        gymClass.setClassName("Pilates");
        gymClass.setTrainer("Anna");

        when(gymClassRepository.existsByClassNameAndTrainer("Pilates", "Anna")).thenReturn(false);

        // Act
        gymClassService.save(gymClass);

        // Assert
        verify(gymClassRepository, times(1)).save(gymClass);
    }

    /**
     * Tests that deleting a gym class with active bookings throws RuntimeException.
     */
    @Test
    @DisplayName("Should throw exception when gym class has active bookings")
    void delete_shouldThrowException_whenGymClassHasBookings() {
        // Arrange
        GymClass gymClass = new GymClass();
        gymClass.setClassName("Yoga");

        when(bookingRepository.existsByGymClass(gymClass)).thenReturn(true);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            gymClassService.delete(gymClass);
        });

        assertTrue(exception.getMessage().contains("has active bookings"));
    }

    /**
     * Tests that deleting a gym class without active bookings deletes successfully.
     */
    @Test
    @DisplayName("Should delete gym class when no active bookings exist")
    void delete_shouldDeleteGymClass_whenNoActiveBookings() {
        // Arrange
        GymClass gymClass = new GymClass();

        when(bookingRepository.existsByGymClass(gymClass)).thenReturn(false);

        // Act
        gymClassService.delete(gymClass);

        // Assert
        verify(gymClassRepository, times(1)).delete(gymClass);
    }
}