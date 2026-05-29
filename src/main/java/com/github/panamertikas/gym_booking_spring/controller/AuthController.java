package com.github.panamertikas.gym_booking_spring.controller;

import com.github.panamertikas.gym_booking_spring.dto.AuthRequestDTO;
import com.github.panamertikas.gym_booking_spring.dto.AuthResponseDTO;
import com.github.panamertikas.gym_booking_spring.model.Member;
import com.github.panamertikas.gym_booking_spring.model.MembershipType;
import com.github.panamertikas.gym_booking_spring.model.User;
import com.github.panamertikas.gym_booking_spring.repository.MemberRepository;
import com.github.panamertikas.gym_booking_spring.repository.UserRepository;
import com.github.panamertikas.gym_booking_spring.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for authentication endpoints (register and login).
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private MemberRepository memberRepository;

    /**
     * Registers a new user.
     * @param dto the registration request containing username and password
     * @return success message
     */
    @PostMapping("/register")
    public String register(@RequestBody AuthRequestDTO dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("Username already exists!");
        }

        // Create Member
        Member member = new Member();
        member.setFirstname(dto.getFirstname());
        member.setLastname(dto.getLastname());
        member.setMail(dto.getUsername());
        member.setAge(dto.getAge());
        member.setMembershipType(MembershipType.DAILY);
        memberRepository.save(member);

        // Create User
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole("USER");
        user.setMember(member);
        userRepository.save(user);

        return "User registered successfully!";
    }

    /**
     * Authenticates a user and returns a JWT token.
     * @param dto the login request containing username and password
     * @return JWT token
     */
    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody AuthRequestDTO dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
        );
        String token = jwtUtil.generateToken(dto.getUsername());
        return new AuthResponseDTO(token);
    }

    @GetMapping("/me")
    public Object me(Authentication authentication) {
        if (authentication == null) return "No authentication - anonymous user";
        return authentication.getAuthorities().toString();
    }
}