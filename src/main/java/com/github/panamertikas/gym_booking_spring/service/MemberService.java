package com.github.panamertikas.gym_booking_spring.service;

import com.github.panamertikas.gym_booking_spring.model.Member;
import com.github.panamertikas.gym_booking_spring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public void save (Member member){
        memberRepository.save(member);
    }

    public void delete (Member member) {
        memberRepository.delete(member);
    }

    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

}
