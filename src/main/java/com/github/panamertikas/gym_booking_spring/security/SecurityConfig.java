package com.github.panamertikas.gym_booking_spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security configuration class that defines the security rules for the application.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    /**
     * Defines the security filter chain with rules for each endpoint.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/", "/index.html", "/gymclasses.html", "/bookings.html", "/login.html", "/dashboard.html", "/my-bookings.html").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/gym_classes/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/members/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/bookings/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/api/members/me").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/api/members/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/api/members/**").hasRole("ADMIN")
                        .requestMatchers("/api/gym_classes/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/api/bookings/availability/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/api/bookings/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/", "/index.html", "/gymclasses.html", "/bookings.html", "/login.html", "/dashboard.html", "/my-bookings.html", "/profile.html").permitAll()
                        .requestMatchers("/", "/index.html", "/gymclasses.html", "/bookings.html", "/login.html", "/dashboard.html", "/my-bookings.html", "/profile.html", "/register-member.html").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * Password encoder using BCrypt hashing algorithm.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Authentication manager bean.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}