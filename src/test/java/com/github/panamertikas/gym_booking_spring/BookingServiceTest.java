package com.github.panamertikas.gym_booking_spring;

import com.github.panamertikas.gym_booking_spring.model.Booking;
import com.github.panamertikas.gym_booking_spring.model.GymClass;
import com.github.panamertikas.gym_booking_spring.model.Member;
import com.github.panamertikas.gym_booking_spring.repository.BookingRepository;
import com.github.panamertikas.gym_booking_spring.repository.GymClassRepository;
import com.github.panamertikas.gym_booking_spring.repository.MemberRepository;
import com.github.panamertikas.gym_booking_spring.service.BookingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for BookingService.
 * Uses Mockito to mock dependencies and test business logic in isolation.
 */
@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private GymClassRepository gymClassRepository;

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private BookingService bookingService;

    /**
     * Tests that saving a booking when class is full throws RuntimeException.
     */
    @Test
    @DisplayName("Should throw exception when gym class is full")
    void save_shouldThrowException_whenGymClassIsFull() {
        // Arrange
        GymClass gymClass = new GymClass();
        gymClass.setId(1L);
        gymClass.setClassName("Yoga");
        gymClass.setCurrentCapacity(10);
        gymClass.setMaxCapacity(10);

        Member member = new Member();
        member.setId(1L);

        Booking booking = new Booking();
        booking.setDate("2024-01-01");

        when(gymClassRepository.findById(1L)).thenReturn(Optional.of(gymClass));
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            bookingService.save(booking, 1L, 1L);
        });

        assertTrue(exception.getMessage().contains("is full"));
    }

    /**
     * Tests that saving a duplicate booking throws RuntimeException.
     */
    @Test
    @DisplayName("Should throw exception when member already has booking for this class")
    void save_shouldThrowException_whenDuplicateBooking() {
        // Arrange
        GymClass gymClass = new GymClass();
        gymClass.setId(1L);
        gymClass.setClassName("Yoga");
        gymClass.setCurrentCapacity(5);
        gymClass.setMaxCapacity(10);

        Member member = new Member();
        member.setId(1L);
        member.setMail("test@gmail.com");

        Booking booking = new Booking();
        booking.setDate("2024-01-01");

        when(gymClassRepository.findById(1L)).thenReturn(Optional.of(gymClass));
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        when(bookingRepository.existsByMemberAndGymClassAndDate(member, gymClass, "2024-01-01")).thenReturn(true);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            bookingService.save(booking, 1L, 1L);
        });

        assertTrue(exception.getMessage().contains("already has a booking"));
    }

    /**
     * Tests that saving a valid booking increases capacity and saves successfully.
     */
    @Test
    @DisplayName("Should save booking and increase capacity when valid")
    void save_shouldSaveBooking_whenValid() {
        // Arrange
        GymClass gymClass = new GymClass();
        gymClass.setId(1L);
        gymClass.setClassName("Yoga");
        gymClass.setCurrentCapacity(5);
        gymClass.setMaxCapacity(10);

        Member member = new Member();
        member.setId(1L);

        Booking booking = new Booking();
        booking.setDate("2024-01-01");

        when(gymClassRepository.findById(1L)).thenReturn(Optional.of(gymClass));
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        when(bookingRepository.existsByMemberAndGymClassAndDate(member, gymClass, "2024-01-01")).thenReturn(false);

        // Act
        bookingService.save(booking, 1L, 1L);

        // Assert
        assertEquals(6, gymClass.getCurrentCapacity());
        verify(bookingRepository, times(1)).save(booking);
    }
}