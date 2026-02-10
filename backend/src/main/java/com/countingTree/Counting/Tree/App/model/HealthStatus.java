package com.countingTree.Counting.Tree.App.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "health_statuses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HealthStatus {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statusId;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    // -------------------------------------------------------- RELATIONS

    @OneToMany(mappedBy = "healthStatus", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Plant> plants = new HashSet<>();

}
