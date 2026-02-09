package com.countingTree.Counting.Tree.App.model;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Entity
@Table(name = "species")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Specie {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long specieId;

    @Column(name = "common_name", nullable = false)
    private String commonName;

    @Column(name = "scientific_name", nullable = false, unique = true)
    private String scientificName;

    @Column(name = "description")
    private String description;

    // ------------------------------------------------------------ RELATIONS

    @OneToMany(mappedBy = "species")
    @JsonManagedReference("specie-plants")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Plant> plants = new HashSet<>();

}
