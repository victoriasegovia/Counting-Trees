package com.countingTree.Counting.Tree.App.model;

import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    // -------------------------------------------------------- RELATIONS

    @Lob
    @Column(name = "profile_picture", nullable = true)
    private byte[] profilePicture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "plantedBy", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("user-plants")
    private Set<Plant> plantsRegistered = new HashSet<>();

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("user-alerts-created")
    private Set<Alert> alertsCreated = new HashSet<>();

    @OneToMany(mappedBy = "resolvedBy", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("user-alerts-resolved")
    private Set<Alert> alertsResolved = new HashSet<>();

}
