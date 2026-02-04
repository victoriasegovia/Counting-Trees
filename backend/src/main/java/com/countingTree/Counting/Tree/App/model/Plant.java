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

        public Plant(Long plantId, LocalDateTime datePlanted, Specie specie, User plantedBy,
            PlantVerificationStatus plantVerificationStatus, HealthStatus healthStatus, Set<Photo> photos,
            Set<Note> notes, Set<Alert> alerts) {
        this.plantId = plantId;
        this.datePlanted = datePlanted;
        this.specie = specie;
        this.plantedBy = plantedBy;
        this.plantVerificationStatus = plantVerificationStatus;
        this.healthStatus = healthStatus;
        this.photos = photos;
        this.notes = notes;
        this.alerts = alerts;
    }

    public Plant() {}

    public Long getPlantId() {
        return plantId;
    }

    public void setPlantId(Long plantId) {
        this.plantId = plantId;
    }

    public LocalDateTime getDatePlanted() {
        return datePlanted;
    }

    public void setDatePlanted(LocalDateTime datePlanted) {
        this.datePlanted = datePlanted;
    }

    public Specie getSpecie() {
        return specie;
    }

    public void setSpecie(Specie specie) {
        this.specie = specie;
    }

    public User getPlantedBy() {
        return plantedBy;
    }

    public void setPlantedBy(User plantedBy) {
        this.plantedBy = plantedBy;
    }

    public PlantVerificationStatus getPlantVerificationStatus() {
        return plantVerificationStatus;
    }

    public void setPlantVerificationStatus(PlantVerificationStatus plantVerificationStatus) {
        this.plantVerificationStatus = plantVerificationStatus;
    }

    public HealthStatus getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(HealthStatus healthStatus) {
        this.healthStatus = healthStatus;
    }

    public Set<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }

    public Set<Note> getNotes() {
        return notes;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }

    public Set<Alert> getAlerts() {
        return alerts;
    }

    public void setAlerts(Set<Alert> alerts) {
        this.alerts = alerts;
    }

}
