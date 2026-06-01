package com.github.panamertikas.gym_booking_spring.service;

import com.github.panamertikas.gym_booking_spring.model.Member;
import com.github.panamertikas.gym_booking_spring.repository.BookingRepository;
import com.github.panamertikas.gym_booking_spring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BookingRepository bookingRepository;

    public void save(Member member) {
        if (memberRepository.existsByMail(member.getMail())) {
            throw new RuntimeException("Member with email " + member.getMail() + " already exists!");
        }
        memberRepository.save(member);
    }

    public void delete(Member member) {
        if (bookingRepository.existsByMember(member)) {
            throw new RuntimeException("Member with email " + member.getMail() + " has active bookings and cannot be deleted!");
        }
        memberRepository.delete(member);
    }

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

    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

}
