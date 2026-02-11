package com.countingTree.Counting.Tree.App.config;

import com.countingTree.Counting.Tree.App.model.*;
import com.countingTree.Counting.Tree.App.repository.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final SpecieRepository specieRepository;
    private final PlantRepository plantRepository;
    private final AlertTypeRepository alertTypeRepository;
    private final AlertRepository alertRepository;
    private final HealthStatusRepository healthStatusRepository;
    private final NoteRepository noteRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        // ---------- USER ADMIN
        User admin = userRepository.findByEmail("admin@email.com");
        if (admin == null) {
            admin = userRepository.save(
                    User.builder()
                            .firstName("Super")
                            .lastName("Admin")
                            .email("admin@email.com")
                            .password(passwordEncoder.encode("123"))
                            .role(Role.ADMIN)
                            .build()
            );
        }

        // ---------- USER GUARDIAN
        User guardian = userRepository.findByEmail("guardian@email.com");
        if (guardian == null) {
            userRepository.save(
                    User.builder()
                            .firstName("Miss")
                            .lastName("Guardian")
                            .email("guardian@email.com")
                            .password(passwordEncoder.encode("123"))
                            .role(Role.GUARDIAN)
                            .build()
            );
        }

        // ---------- USER BOTANIST
        User botanist = userRepository.findByEmail("botanist@email.com");
        if (botanist == null) {
            userRepository.save(
                    User.builder()
                            .firstName("Mr.")
                            .lastName("Botanist")
                            .email("botanist@email.com")
                            .password(passwordEncoder.encode("123"))
                            .role(Role.GUARDIAN)
                            .build()
            );
        }

        HealthStatus healthy = healthStatusRepository.findByName("HEALTHY");
        if (healthy == null) {
            healthy = healthStatusRepository.save(
                    HealthStatus.builder()
                            .name("HEALTHY")
                            .description("Plants are growing healthy.")
                            .build()
            );
        }

        Specie ficus = specieRepository.findByCommonName("Ficus");
        if (ficus == null) {
            ficus = specieRepository.save(
                    Specie.builder()
                            .commonName("Ficus")
                            .scientificName("Ficus Maximus")
                            .description("Mediterranean specie with green leaves.")
                            .build()
            );
        }

        Plant plant = plantRepository.findById(1L).orElse(null);
        if (plant == null) {
            plant = plantRepository.save(
                    Plant.builder()
                            .latitude(100.00)
                            .longitude(100.00)
                            .datePlanted(now())
                            .specie(ficus)
                            .plantedBy(admin)
                            .plantVerificationStatus(PlantVerificationStatus.VERIFIED)
                            .healthStatus(healthy)
                            .build()
            );
        }

        Note note = noteRepository.findById(1L).orElse(null);
        if (note == null) {
            note = noteRepository.save(
                    Note.builder()
                            .text("Plant has new leaves!")
                            .dateCreated(now())
                            .user(admin)
                            .plant(plant)
                            .build()
            );
        }

        AlertType temperature = alertTypeRepository.findByName("TEMPERATURE");
        if (temperature == null) {
            temperature = alertTypeRepository.save(
                    AlertType.builder()
                            .name("TEMPERATURE")
                            .description("Temperature out of range")
                            .build()
            );
        }

        if (alertRepository.count() == 0) {
            alertRepository.save(
                    Alert.builder()
                            .creationDate(now())
                            .status(AlertStatus.OPEN)
                            .alertType(temperature)
                            .plant(plant)
                            .createdBy(admin)
                            .build()
            );
        }

        System.out.println("🌱 Seeder running...");

    }
}
