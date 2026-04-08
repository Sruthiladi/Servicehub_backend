package com.klu.servicehubBackend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(Customizer.withDefaults())
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth

                // Public auth endpoints
                .requestMatchers("/api/auth/**").permitAll()

                // Swagger / OpenAPI (for future use)
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()

                // Public read endpoints
                .requestMatchers(HttpMethod.GET, "/api/services/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/reviews/**").permitAll()

             // USER role
                .requestMatchers("/api/bookings/user/**").hasRole("USER")
                .requestMatchers(HttpMethod.POST, "/api/bookings").hasRole("USER")
                .requestMatchers(HttpMethod.POST, "/api/reviews").hasRole("USER")

                // ✅ USER can create support tickets
                .requestMatchers(HttpMethod.POST, "/api/support").hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/api/support/user/**").hasRole("USER")

                // PROFESSIONAL role
                .requestMatchers("/api/bookings/professional/**").hasRole("PROFESSIONAL")
                .requestMatchers("/api/professional/**").hasRole("PROFESSIONAL")

                // ADMIN role
                .requestMatchers("/api/admin/**").hasRole("ADMIN")

                
                .requestMatchers(HttpMethod.GET, "/api/support").hasRole("SUPPORT")
                .requestMatchers(HttpMethod.GET, "/api/support/active").hasRole("SUPPORT")
                .requestMatchers(HttpMethod.GET, "/api/support/resolved").hasRole("SUPPORT")
                .requestMatchers(HttpMethod.PUT, "/api/support/**").hasRole("SUPPORT")
                // Everything else requires authentication
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}