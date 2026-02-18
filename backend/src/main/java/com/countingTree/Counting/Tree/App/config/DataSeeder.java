package com.countingTree.Counting.Tree.App.config;

import com.countingTree.Counting.Tree.App.model.*;
import com.countingTree.Counting.Tree.App.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

import static java.time.LocalDateTime.now;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataSeeder implements CommandLineRunner {

        private final UserRepository userRepository;
        private final SpecieRepository specieRepository;
        private final PlantRepository plantRepository;
        private final AlertTypeRepository alertTypeRepository;
        private final AlertRepository alertRepository;
        private final HealthStatusRepository healthStatusRepository;
        private final NoteRepository noteRepository;

        private final PasswordEncoder passwordEncoder;

        // Fixed dummy photo (1 pixel red dot)
        private static final byte[] DUMMY_PHOTO = Base64.getDecoder()
                        .decode("R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7");

        @Override
        public void run(String... args) {
                log.info("🌱 Starting Data Seeding...");

                Map<String, User> users = seedUsers();
                Map<String, Specie> species = seedSpecies();
                Map<String, HealthStatus> healthStatuses = seedHealthStatuses();
                Map<String, AlertType> alertTypes = seedAlertTypes();

                List<Plant> plants = seedPlants(users, species, healthStatuses);

                seedNotes(plants, users);
                seedAlerts(plants, users, alertTypes);

                log.info("🌳 Data Seeding Complete!");
        }

        private Map<String, User> seedUsers() {
                log.info("Seeding Users...");
                Map<String, User> userMap = new HashMap<>();

                userMap.put("ADMIN", createUser("Super", "Admin", "admin@email.com", Role.ADMIN));
                userMap.put("BOTANIST", createUser("Dr.", "Botanist", "botanist@email.com", Role.BOTANIST));
                userMap.put("GUARDIAN", createUser("Miss", "Guardian", "guardian@email.com", Role.GUARDIAN));
                userMap.put("USER", createUser("John", "Doe", "user@email.com", Role.USER));

                return userMap;
        }

        private User createUser(String firstName, String lastName, String email, Role role) {
                User user = userRepository.findByEmail(email);
                if (user == null) {
                        user = userRepository.save(
                                        User.builder()
                                                        .firstName(firstName)
                                                        .lastName(lastName)
                                                        .email(email)
                                                        .password(passwordEncoder.encode("123"))
                                                        .role(role)
                                                        .profilePicture(DUMMY_PHOTO)
                                                        .build());
                }
                return user;
        }

        private Map<String, Specie> seedSpecies() {
                log.info("Seeding Species...");
                Map<String, Specie> specieMap = new HashMap<>();

                specieMap.put("FICUS", createSpecie("Ficus", "Ficus Maximus",
                                "Mediterranean specie with large shiny green leaves."));
                specieMap.put("OAK", createSpecie("Oak", "Quercus Robur",
                                "Large deciduous tree known for its strength and longevity."));
                specieMap.put("PINE", createSpecie("Pine", "Pinus Sylvestris",
                                "Coniferous evergreen tree growing up to 35m height."));
                specieMap.put("ROSE",
                                createSpecie("Rose", "Rosa", "Woody perennial flowering plant of the genus Rosa."));
                specieMap.put("MAPLE", createSpecie("Maple", "Acer",
                                "Trees or shrubs famously known for their syrup and colorful autumn leaves."));

                return specieMap;
        }

        private Specie createSpecie(String commonName, String scientificName, String description) {
                Specie specie = specieRepository.findByCommonName(commonName);
                if (specie == null) {
                        specie = specieRepository.save(
                                        Specie.builder()
                                                        .commonName(commonName)
                                                        .scientificName(scientificName)
                                                        .description(description)
                                                        .build());
                }
                return specie;
        }

        private Map<String, HealthStatus> seedHealthStatuses() {
                log.info("Seeding Health Statuses...");
                Map<String, HealthStatus> statusMap = new HashMap<>();

                statusMap.put("HEALTHY",
                                createHealthStatus("HEALTHY", "Plant is growing well and shows no signs of stress."));
                statusMap.put("NEEDS_WATER", createHealthStatus("NEEDS_WATER", "Soil is dry, leaves may be drooping."));
                statusMap.put("PEST",
                                createHealthStatus("PEST_INFESTATION", "Visible signs of insect activity or damage."));
                statusMap.put("WITHERING",
                                createHealthStatus("WITHERING", "Plant is showing severe signs of decline."));
                statusMap.put("RECOVERING", createHealthStatus("RECOVERING",
                                "Plant is showing new growth after a period of stress."));

                return statusMap;
        }

        private HealthStatus createHealthStatus(String name, String description) {
                HealthStatus status = healthStatusRepository.findByName(name);
                if (status == null) {
                        status = healthStatusRepository.save(
                                        HealthStatus.builder()
                                                        .name(name)
                                                        .description(description)
                                                        .build());
                }
                return status;
        }

        private Map<String, AlertType> seedAlertTypes() {
                log.info("Seeding Alert Types...");
                Map<String, AlertType> typeMap = new HashMap<>();

                typeMap.put("TEMP", createAlertType("TEMPERATURE", "Ambient temperature is outside optimal range."));
                typeMap.put("WATER",
                                createAlertType("WATERING", "Plant needs immediate watering or irrigation check."));
                typeMap.put("PEST", createAlertType("PEST", "Insects or pests detected on the plant."));
                typeMap.put("DAMAGE", createAlertType("PHYSICAL_DAMAGE",
                                "Broken branches, vandalism, or accidental damage."));
                typeMap.put("NUTRIENT",
                                createAlertType("FERTILIZER", "Signs of nutrient deficiency (yellowing leaves, etc)."));

                return typeMap;
        }

        private AlertType createAlertType(String name, String description) {
                AlertType type = alertTypeRepository.findByName(name);
                if (type == null) {
                        type = alertTypeRepository.save(
                                        AlertType.builder()
                                                        .name(name)
                                                        .description(description)
                                                        .build());
                }
                return type;
        }

        private List<Plant> seedPlants(Map<String, User> users, Map<String, Specie> species,
                        Map<String, HealthStatus> health) {
                log.info("Seeding Plants...");
                List<Plant> plants = new ArrayList<>();

                // 1. Ficus by Admin (Verified, Healthy)
                plants.add(createPlant(37.16920, -3.57447, species.get("FICUS"), users.get("ADMIN"),
                                PlantVerificationStatus.VERIFIED, health.get("HEALTHY")));

                // 2. Oak by Guardian (Pending, Needs Water)
                plants.add(createPlant(37.16925, -3.57445, species.get("OAK"), users.get("GUARDIAN"),
                                PlantVerificationStatus.PENDING, health.get("NEEDS_WATER")));

                // 3. Pine by Botanist (Verified, Pest)
                plants.add(createPlant(37.16920, -3.57440, species.get("PINE"), users.get("BOTANIST"),
                                PlantVerificationStatus.VERIFIED, health.get("PEST")));

                // 4. Rose by Guardian (Pending, Withering) - Rose might be tricky in some
                // climates!
                plants.add(createPlant(37.16915, -3.57442, species.get("ROSE"), users.get("GUARDIAN"),
                                PlantVerificationStatus.PENDING, health.get("WITHERING")));

                // 5. Maple by Admin (Verified, Recovering)
                plants.add(createPlant(37.16922, -3.57450, species.get("MAPLE"), users.get("ADMIN"),
                                PlantVerificationStatus.VERIFIED, health.get("RECOVERING")));

                // 6. Another Ficus by Guardian (Rejected?, Healthy) - Maybe user made a mistake
                plants.add(createPlant(37.16918, -3.57455, species.get("FICUS"), users.get("GUARDIAN"),
                                PlantVerificationStatus.REJECTED, health.get("HEALTHY")));

                return plants;
        }

        private Plant createPlant(Double lat, Double lon, Specie specie, User plantedBy,
                        PlantVerificationStatus verifyStatus, HealthStatus health) {
                // Simple check to avoid duplicates on every run in this demo seeder
                // Real implementations might use a more robust check (e.g. by lat/lon)
                return plantRepository.save(
                                Plant.builder()
                                                .latitude(lat)
                                                .longitude(lon)
                                                .datePlanted(now())
                                                .specie(specie)
                                                .plantedBy(plantedBy)
                                                .plantVerificationStatus(verifyStatus)
                                                .healthStatus(health)
                                                .photo(DUMMY_PHOTO)
                                                .build());
        }

        private void seedNotes(List<Plant> plants, Map<String, User> users) {
                log.info("Seeding Notes...");
                if (noteRepository.count() > 0)
                        return;

                Plant p1 = plants.get(0); // Ficus
                createNote("Plant looks great!", users.get("ADMIN"), p1);
                createNote("Checked roots, all good.", users.get("BOTANIST"), p1);

                Plant p2 = plants.get(1); // Oak (Needs water)
                createNote("Soil is very dry here.", users.get("GUARDIAN"), p2);
                createNote("Will bring water tomorrow.", users.get("USER"), p2);

                Plant p3 = plants.get(2); // Pine (Pest)
                createNote("Found beetles on the bark.", users.get("BOTANIST"), p3);
        }

        private void createNote(String text, User user, Plant plant) {
                noteRepository.save(
                                Note.builder()
                                                .text(text)
                                                .dateCreated(now())
                                                .user(user)
                                                .plant(plant)
                                                .build());
        }

        private void seedAlerts(List<Plant> plants, Map<String, User> users, Map<String, AlertType> types) {
                log.info("Seeding Alerts...");
                if (alertRepository.count() > 0)
                        return;

                // Alert on Oak (Needs Water)
                createAlert(types.get("WATER"), plants.get(1), users.get("GUARDIAN"), AlertStatus.OPEN);

                // Alert on Pine (Pest)
                createAlert(types.get("PEST"), plants.get(2), users.get("BOTANIST"), AlertStatus.PENDING);

                // Resolved Alert on Maple
                Alert recovered = createAlert(types.get("DAMAGE"), plants.get(4), users.get("ADMIN"),
                                AlertStatus.RESOLVED);
                recovered.setResolvedBy(users.get("GUARDIAN")); // Someone resolved it
                alertRepository.save(recovered);
        }

        private Alert createAlert(AlertType type, Plant plant, User user, AlertStatus status) {
                return alertRepository.save(
                                Alert.builder()
                                                .alertType(type)
                                                .plant(plant)
                                                .createdBy(user)
                                                .status(status)
                                                .creationDate(now())
                                                .build());
        }
}
