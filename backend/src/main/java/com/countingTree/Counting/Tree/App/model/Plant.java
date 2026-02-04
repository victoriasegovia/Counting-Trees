package com.countingTree.Counting.Tree.App.model;

import java.time.LocalDateTime;
import java.util.*;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

@Entity
@Table(name = "plants")
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long plantId;

    @Column(name = "date_planted")
    private LocalDateTime datePlanted;

    // -------------------------------------------------------- RELATIONS

    @ManyToOne
    @JoinColumn(name = "species_id", nullable = false)
    @JsonBackReference("specie-plants")
    private Specie specie;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    @JsonBackReference("user-plants")
    private User plantedBy;

    @Enumerated(EnumType.STRING)
    @Column(name = "verification_status", nullable = false)
    private PlantVerificationStatus plantVerificationStatus = PlantVerificationStatus.PENDING;

    @ManyToOne
    @JoinColumn(name = "health_status_id")
    @JsonBackReference("plant-health")
    private HealthStatus healthStatus;

    @OneToMany(mappedBy = "plant", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("plant-photos")
    private Set<Photo> photos = new HashSet<>();

    @OneToMany(mappedBy = "plant", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("plant-notes")
    private Set<Note> notes = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "alert_id")
    @JsonManagedReference("plant-alerts")
    private Set<Alert> alerts = new HashSet<>();

    // -------------------------------------------------------- CONSTRUCTORS, GETTERS AND SETTERS



}
