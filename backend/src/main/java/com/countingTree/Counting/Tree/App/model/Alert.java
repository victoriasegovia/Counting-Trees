package com.countingTree.Counting.Tree.App.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Entity
@Table(name = "alerts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alert_id")
    private Long alertId;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    // -------------------------------------------------------- RELATIONS
    @Enumerated(EnumType.STRING)
    private AlertStatus status = AlertStatus.PENDING;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alert_type_id", nullable = false)
    @JsonBackReference("type-alerts")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private AlertType alertType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plant_id", nullable = false)
    @JsonBackReference("plant-alerts")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Plant plant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    @JsonBackReference("user-alerts-created")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resolved_by")
    @JsonBackReference("user-alerts-resolved")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User resolvedBy;

}
