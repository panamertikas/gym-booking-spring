package com.github.panamertikas.gym_booking_spring;

import com.github.panamertikas.gym_booking_spring.model.*;
import com.github.panamertikas.gym_booking_spring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired private UserRepository userRepository;
    @Autowired private MemberRepository memberRepository;
    @Autowired private GymClassRepository gymClassRepository;
    @Autowired private BookingRepository bookingRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        // Admin user
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("ADMIN");
            userRepository.save(admin);
        }

        // Member for user
        if (!memberRepository.existsByMail("user@gmail.com")) {
            Member member = new Member();
            member.setFirstname("Nikos");
            member.setLastname("Papadopoulos");
            member.setAge(25);
            member.setMail("user@gmail.com");
            member.setMembershipType(MembershipType.MONTHLY);
            memberRepository.save(member);

            // User
            if (!userRepository.existsByUsername("user@gmail.com")) {
                User user = new User();
                user.setUsername("user@gmail.com");
                user.setPassword(passwordEncoder.encode("123456"));
                user.setRole("USER");
                user.setMember(member);
                userRepository.save(user);
            }
        }

        // Gym Classes
        if (gymClassRepository.count() == 0) {
            GymClass yoga = new GymClass();
            yoga.setClassName("Yoga");
            yoga.setTrainer("Maria Papadopoulou");
            yoga.setMaxCapacity(10);
            yoga.setCurrentCapacity(0);
            gymClassRepository.save(yoga);

            GymClass pilates = new GymClass();
            pilates.setClassName("Pilates");
            pilates.setTrainer("Anna Georgiou");
            pilates.setMaxCapacity(15);
            pilates.setCurrentCapacity(0);
            gymClassRepository.save(pilates);

            GymClass crossfit = new GymClass();
            crossfit.setClassName("CrossFit");
            crossfit.setTrainer("Nikos Dimitriou");
            crossfit.setMaxCapacity(20);
            crossfit.setCurrentCapacity(0);
            gymClassRepository.save(crossfit);

            GymClass spinning = new GymClass();
            spinning.setClassName("Spinning");
            spinning.setTrainer("Elena Kostopoulos");
            spinning.setMaxCapacity(12);
            spinning.setCurrentCapacity(0);
            gymClassRepository.save(spinning);
        }
    }
}