package com.countingTree.Counting.Tree.App.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF for simplicity (REST API scenario)
                .csrf(csrf -> csrf.disable())

                // Authorization rules
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/users/welcome").permitAll()
                        .requestMatchers("/users/guardian/**").hasRole("GUARDIAN")
                        .requestMatchers("/users/botanist/**").hasRole("BOTANIST")
                        .requestMatchers("/users/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )

                // Form login configuration
                //.formLogin(form -> form
                //        .defaultSuccessUrl("/auth/welcome", true) // Redirect after login
                // )

                // Enable HTTP Basic authentication as well
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
