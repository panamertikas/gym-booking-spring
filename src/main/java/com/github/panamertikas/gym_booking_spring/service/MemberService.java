package com.github.panamertikas.gym_booking_spring.service;

import com.github.panamertikas.gym_booking_spring.model.Member;
import com.github.panamertikas.gym_booking_spring.repository.BookingRepository;
import com.github.panamertikas.gym_booking_spring.repository.MemberRepository;
import com.github.panamertikas.gym_booking_spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing gym members.
 */
@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Saves a new member. Throws an exception if the email already exists.
     *
     * @param member the member to save
     */
    public void save(Member member) {
        if (memberRepository.existsByMail(member.getMail())) {
            throw new RuntimeException("Member with email " + member.getMail() + " already exists!");
        }
        memberRepository.save(member);
    }

    /**
     * Deletes a member. Throws an exception if the member has active bookings.
     * Also deletes the associated user account.
     *
     * @param member the member to delete
     */
    public void delete(Member member) {
        if (bookingRepository.existsByMember(member)) {
            throw new RuntimeException("Member with email " + member.getMail() + " has active bookings and cannot be deleted!");
        }
        userRepository.findByMember(member).ifPresent(userRepository::delete);
        memberRepository.delete(member);
    }

    /**
     * Updates an existing member's information.
     *
     * @param id the ID of the member to update
     * @param updatedMember the new member data
     */
    public void update(Long id, Member updatedMember) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found!"));
        member.setFirstname(updatedMember.getFirstname());
        member.setLastname(updatedMember.getLastname());
        member.setMail(updatedMember.getMail());
        member.setAge(updatedMember.getAge());
        member.setMembershipType(updatedMember.getMembershipType());
        memberRepository.save(member);
    }

    /**
     * Finds a member by ID.
     *
     * @param id the member ID
     * @return an Optional containing the member if found
     */
    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }

    /**
     * Returns all members.
     *
     * @return list of all members
     */
    public List<Member> findAll() {
        return memberRepository.findAll();
    }
}