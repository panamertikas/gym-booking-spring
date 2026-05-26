package com.github.panamertikas.gym_booking_spring.controller;

import com.github.panamertikas.gym_booking_spring.dto.MemberMapper;
import com.github.panamertikas.gym_booking_spring.dto.MemberRequestDTO;
import com.github.panamertikas.gym_booking_spring.dto.MemberResponseDTO;
import com.github.panamertikas.gym_booking_spring.model.Member;
import com.github.panamertikas.gym_booking_spring.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/members")
    public MemberResponseDTO save(@Valid @RequestBody MemberRequestDTO dto) {
        Member member = MemberMapper.toEntity(dto);
        memberService.save(member);
        return MemberMapper.toResponseDTO(member);
    }

    @DeleteMapping("/members/{id}")
    public void delete(@PathVariable Long id) {
        memberService.findById(id).ifPresent(memberService::delete);
    }

    @GetMapping("/members/{id}")
    public MemberResponseDTO findById(@PathVariable Long id) {
        return memberService.findById(id)
                .map(MemberMapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Member not found!"));
    }

    @GetMapping("/members")
    public List<MemberResponseDTO> findAll() {
        return memberService.findAll()
                .stream()
                .map(MemberMapper::toResponseDTO)
                .toList();
    }
}