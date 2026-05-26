package com.github.panamertikas.gym_booking_spring.controller;

import com.github.panamertikas.gym_booking_spring.model.Member;
import com.github.panamertikas.gym_booking_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/members")
    public void save (@RequestBody Member member){
        memberService.save(member);
    }

    @DeleteMapping("/members/{id}")
    public void delete (@PathVariable Long id) {
        memberService.findById(id).ifPresent(memberService::delete);
    }

    @GetMapping("/members/{id}")
    public Optional<Member> findById(@PathVariable Long id) {
        return memberService.findById(id);
    }

    @GetMapping("/members")
    public List<Member> findAll() {
        return memberService.findAll();
    }
}
