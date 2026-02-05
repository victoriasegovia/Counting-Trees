package com.countingTree.Counting.Tree.App.model;

import java.time.LocalDateTime;
import java.util.*;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.*;

@Entity
@Table(name = "alerts")
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alertId;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    // -------------------------------------------------------- RELATIONS
    @Enumerated(EnumType.STRING)
    private AlertStatus status = AlertStatus.PENDING;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alert_type_id", nullable = false)
    @JsonBackReference("type-alerts")
    private AlertType alertType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plant_id", nullable = false)
    @JsonBackReference("plant-alerts")
    private Plant plant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    @JsonBackReference("user-alerts-created")
    private User createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resolved_by")
    @JsonBackReference("user-alerts-resolved")
    private User resolvedBy;

    // -------------------------------------------------------- CONSTRUCTORS, GETTERS AND SETTERS
    public Alert(Long alertId, AlertType alertType, LocalDateTime creationDate, AlertStatus status, Plant plant, User createdBy, User resolvedBy) {
        this.alertId = alertId;
        this.alertType = alertType;
        this.creationDate = creationDate;
        this.status = status;
        this.plant = plant;
        this.createdBy = createdBy;
        this.resolvedBy = resolvedBy;
    }

    public Alert() {
    }

    public Long getAlertId() {
        return alertId;
    }

    public void setAlertId(Long alertId) {
        this.alertId = alertId;
    }

    public AlertType getAlertType() {
        return alertType;
    }

    public void setAlertType(AlertType alertType) {
        this.alertType = alertType;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public AlertStatus getStatus() {
        return status;
    }

    public void setStatus(AlertStatus status) {
        this.status = status;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public User getCreator() {
        return createdBy;
    }

    public void setCreator(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getResolver() {
        return resolvedBy;
    }

    public void setResolver(User resolvedBy) {
        this.resolvedBy = resolvedBy;
    }

}
