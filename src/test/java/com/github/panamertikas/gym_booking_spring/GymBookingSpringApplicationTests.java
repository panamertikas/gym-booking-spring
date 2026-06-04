package com.github.panamertikas.gym_booking_spring;

import com.github.panamertikas.gym_booking_spring.model.Member;
import com.github.panamertikas.gym_booking_spring.repository.BookingRepository;
import com.github.panamertikas.gym_booking_spring.repository.MemberRepository;
import com.github.panamertikas.gym_booking_spring.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for MemberService.
 * Uses Mockito to mock dependencies and test business logic in isolation.
 */
@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private MemberService memberService;

    /**
     * Tests that saving a member with an existing email throws a RuntimeException.
     */
    @Test
    @DisplayName("Should throw exception when email already exists")
    void save_shouldThrowException_whenEmailAlreadyExists() {
        // Arrange
        Member member = new Member();
        member.setMail("test@gmail.com");

        when(memberRepository.existsByMail("test@gmail.com")).thenReturn(true);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            memberService.save(member);
        });

        assertEquals("Member with email test@gmail.com already exists!", exception.getMessage());
    }

    /**
     * Tests that saving a member with a new email saves successfully.
     */
    @Test
    @DisplayName("Should save member when email does not exist")
    void save_shouldSaveMember_whenEmailDoesNotExist() {
        // Arrange
        Member member = new Member();
        member.setMail("new@gmail.com");

        when(memberRepository.existsByMail("new@gmail.com")).thenReturn(false);

        // Act
        memberService.save(member);

        // Assert
        verify(memberRepository, times(1)).save(member);
    }

    /**
     * Tests that deleting a member with active bookings throws a RuntimeException.
     */
    @Test
    @DisplayName("Should throw exception when member has active bookings")
    void delete_shouldThrowException_whenMemberHasBookings() {
        // Arrange
        Member member = new Member();
        member.setMail("test@gmail.com");

        when(bookingRepository.existsByMember(member)).thenReturn(true);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            memberService.delete(member);
        });

        assertTrue(exception.getMessage().contains("has active bookings"));
    }

    /**
     * Tests that deleting a member without active bookings deletes successfully.
     */
    @Test
    @DisplayName("Should delete member when no active bookings exist")
    void delete_shouldDeleteMember_whenNoActiveBookings() {
        // Arrange
        Member member = new Member();

        when(bookingRepository.existsByMember(member)).thenReturn(false);

        // Act
        memberService.delete(member);

        // Assert
        verify(memberRepository, times(1)).delete(member);
    }
}