package com.countingTree.Counting.Tree.App.model;

import jakarta.persistence.*;

import java.util.*;
import com.fasterxml.jackson.annotation.*;

@Entity
@Table(name = "alert_types")
public class AlertType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alertTypeId;
    
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", nullable = true)
    private String description;

    @OneToMany(mappedBy = "alertType", fetch = FetchType.LAZY)
    @JsonManagedReference("type-alerts")
    private Set<Alert> alerts = new HashSet<>();

    public AlertType(Long alertTypeId, String name, String description, Set<Alert> alerts) {
        this.alertTypeId = alertTypeId;
        this.name = name;
        this.description = description;
        this.alerts = alerts;
    }

    public AlertType() {}

    public Long getAlertTypeId() {
        return alertTypeId;
    }

    public void setAlertTypeId(Long alertTypeId) {
        this.alertTypeId = alertTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Alert> getAlerts() {
        return alerts;
    }

    public void setAlerts(Set<Alert> alerts) {
        this.alerts = alerts;
    }
    
}
