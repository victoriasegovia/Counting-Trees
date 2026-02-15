package com.countingTree.Counting.Tree.App.model;

import java.time.LocalDateTime;
import java.util.*;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "plants")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long plantId;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @Column(name = "date_planted")
    private LocalDateTime datePlanted;

    // -------------------------------------------------------- RELATIONS
    @ManyToOne
    @JoinColumn(name = "specie_id", nullable = false)
    @JsonBackReference("specie-plants")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Specie specie;

    @ManyToOne
    @JoinColumn(name = "planted_by", nullable = false)
    @JsonBackReference("user-plants")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User plantedBy;

    @Enumerated(EnumType.STRING)
    @Column(name = "verification_status", nullable = false)
    private PlantVerificationStatus plantVerificationStatus = PlantVerificationStatus.PENDING;

    @ManyToOne
    @JoinColumn(name = "health_status_id")
    @JsonBackReference("plant-health")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private HealthStatus healthStatus;

    @Lob
    @Column(name = "photo", columnDefinition = "MEDIUMBLOB")
    private byte[] photo;

    @OneToMany(mappedBy = "plant", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("plant-notes")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Note> notes = new HashSet<>();

    @OneToMany(mappedBy = "plant", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("plant-alerts")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Alert> alerts = new HashSet<>();

}
