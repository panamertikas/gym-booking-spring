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

        // Members και Users
        if (memberRepository.count() == 0) {

            Member m1  = createMember("Nikos",      "Papadopoulos", 25, "nikos@gmail.com",      MembershipType.MONTHLY);  createUser("nikos@gmail.com",      "123456", m1);
            Member m2  = createMember("Maria",      "Georgiou",     30, "maria@gmail.com",      MembershipType.YEARLY);   createUser("maria@gmail.com",      "123456", m2);
            Member m3  = createMember("Kostas",     "Dimitriou",    28, "kostas@gmail.com",     MembershipType.DAILY);    createUser("kostas@gmail.com",     "123456", m3);
            Member m4  = createMember("Elena",      "Nikolaou",     22, "elena@gmail.com",      MembershipType.MONTHLY);  createUser("elena@gmail.com",      "123456", m4);
            Member m5  = createMember("Giorgos",    "Alexiou",      35, "giorgos@gmail.com",    MembershipType.YEARLY);   createUser("giorgos@gmail.com",    "123456", m5);
            Member m6  = createMember("Sofia",      "Ioannou",      27, "sofia@gmail.com",      MembershipType.MONTHLY);  createUser("sofia@gmail.com",      "123456", m6);
            Member m7  = createMember("Dimitris",   "Petrou",       32, "dimitris@gmail.com",   MembershipType.DAILY);    createUser("dimitris@gmail.com",   "123456", m7);
            Member m8  = createMember("Katerina",   "Stavrou",      29, "katerina@gmail.com",   MembershipType.YEARLY);   createUser("katerina@gmail.com",   "123456", m8);
            Member m9  = createMember("Panagiotis", "Karagiorgis",  40, "panagiotis@gmail.com", MembershipType.MONTHLY);  createUser("panagiotis@gmail.com", "123456", m9);
            Member m10 = createMember("Anastasia",  "Theodorou",    24, "anastasia@gmail.com",  MembershipType.DAILY);    createUser("anastasia@gmail.com",  "123456", m10);
            Member m11 = createMember("Vassilis",   "Economou",     38, "vassilis@gmail.com",   MembershipType.YEARLY);   createUser("vassilis@gmail.com",   "123456", m11);
            Member m12 = createMember("Ioanna",     "Makris",       26, "ioanna@gmail.com",     MembershipType.MONTHLY);  createUser("ioanna@gmail.com",     "123456", m12);
            Member m13 = createMember("Alexandros", "Lambrou",      33, "alexandros@gmail.com", MembershipType.DAILY);    createUser("alexandros@gmail.com", "123456", m13);
            Member m14 = createMember("Christina",  "Papadaki",     31, "christina@gmail.com",  MembershipType.YEARLY);   createUser("christina@gmail.com",  "123456", m14);
            Member m15 = createMember("Michalis",   "Katsaros",     45, "michalis@gmail.com",   MembershipType.MONTHLY);  createUser("michalis@gmail.com",   "123456", m15);
            Member m16 = createMember("Despina",    "Vlachos",      23, "despina@gmail.com",    MembershipType.DAILY);    createUser("despina@gmail.com",    "123456", m16);
            Member m17 = createMember("Thanasis",   "Kontogiannis", 36, "thanasis@gmail.com",   MembershipType.YEARLY);   createUser("thanasis@gmail.com",   "123456", m17);
            Member m18 = createMember("Eleni",      "Sotiriou",     28, "eleni@gmail.com",      MembershipType.MONTHLY);  createUser("eleni@gmail.com",      "123456", m18);
            Member m19 = createMember("Petros",     "Manolis",      42, "petros@gmail.com",     MembershipType.DAILY);    createUser("petros@gmail.com",     "123456", m19);
            Member m20 = createMember("Zoe",        "Karageorgiou", 21, "zoe@gmail.com",        MembershipType.YEARLY);   createUser("zoe@gmail.com",        "123456", m20);

            // Bookings
            GymClass yoga     = gymClassRepository.findAll().get(0);
            GymClass pilates  = gymClassRepository.findAll().get(1);
            GymClass crossfit = gymClassRepository.findAll().get(2);
            GymClass spinning = gymClassRepository.findAll().get(3);

            createBooking(m1,  yoga,     "2026-07-01", "09:00");
            createBooking(m2,  yoga,     "2026-07-01", "09:00");
            createBooking(m3,  crossfit, "2026-07-01", "18:00");
            createBooking(m4,  pilates,  "2026-07-02", "10:00");
            createBooking(m5,  spinning, "2026-07-02", "11:00");
            createBooking(m6,  yoga,     "2026-07-03", "09:00");
            createBooking(m7,  crossfit, "2026-07-03", "18:00");
            createBooking(m8,  pilates,  "2026-07-04", "10:00");
            createBooking(m9,  spinning, "2026-07-04", "11:00");
            createBooking(m10, yoga,     "2026-07-05", "09:00");
            createBooking(m11, crossfit, "2026-07-05", "18:00");
            createBooking(m12, pilates,  "2026-07-06", "10:00");
            createBooking(m13, spinning, "2026-07-06", "11:00");
            createBooking(m14, yoga,     "2026-07-07", "09:00");
            createBooking(m15, crossfit, "2026-07-07", "18:00");
            createBooking(m1,  pilates,  "2026-07-08", "10:00");
            createBooking(m2,  spinning, "2026-07-08", "11:00");
            createBooking(m3,  yoga,     "2026-07-09", "09:00");
            createBooking(m4,  crossfit, "2026-07-09", "18:00");
            createBooking(m5,  pilates,  "2026-07-10", "10:00");
        }
    }

    private Member createMember(String firstname, String lastname, int age, String mail, MembershipType membershipType) {
        Member member = new Member();
        member.setFirstname(firstname);
        member.setLastname(lastname);
        member.setAge(age);
        member.setMail(mail);
        member.setMembershipType(membershipType);
        return memberRepository.save(member);
    }

    private void createUser(String username, String password, Member member) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("USER");
        user.setMember(member);
        userRepository.save(user);
    }

    private void createBooking(Member member, GymClass gymClass, String date, String time) {
        Booking booking = new Booking();
        booking.setMember(member);
        booking.setGymClass(gymClass);
        booking.setDate(date);
        booking.setTime(time);
        gymClass.setCurrentCapacity(gymClass.getCurrentCapacity() + 1);
        gymClassRepository.save(gymClass);
        bookingRepository.save(booking);
    }
}