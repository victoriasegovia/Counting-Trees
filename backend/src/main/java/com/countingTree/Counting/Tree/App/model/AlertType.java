package com.countingTree.Counting.Tree.App.model;

import jakarta.persistence.*;

import java.util.*;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Entity
@Table(name = "alert_types")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Alert> alerts = new HashSet<>();

}
