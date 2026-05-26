package com.github.panamertikas.gym_booking_spring.dto;

import com.github.panamertikas.gym_booking_spring.model.Member;

public class MemberMapper {

    public static Member toEntity(MemberRequestDTO dto) {
        Member member = new Member();
        member.setFirstname(dto.getFirstname());
        member.setLastname(dto.getLastname());
        member.setAge(dto.getAge());
        member.setMail(dto.getMail());
        member.setMembershipType(dto.getMembershipType());
        return member;
    }

    public static MemberResponseDTO toResponseDTO(Member member) {
        MemberResponseDTO dto = new MemberResponseDTO();
        dto.setId(member.getId());
        dto.setFirstname(member.getFirstname());
        dto.setLastname(member.getLastname());
        dto.setAge(member.getAge());
        dto.setMail(member.getMail());
        dto.setMembershipType(member.getMembershipType());
        return dto;
    }
}