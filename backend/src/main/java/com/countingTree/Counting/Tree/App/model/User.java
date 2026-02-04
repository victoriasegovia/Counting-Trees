package com.countingTree.Counting.Tree.App.model;

import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
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
    @JsonIgnore
    private String password;

    // -------------------------------------------------------- RELATIONS

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_id", nullable = true)
    private Photo photo;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("user-plants")
    private Set<Plant> plantsRegistered = new HashSet<>();

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("user-alerts-created")
    private Set<Alert> alertsCreated = new HashSet<>();

    @OneToMany(mappedBy = "resolver", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("user-alerts-resolved")
    private Set<Alert> alertsResolved = new HashSet<>();

    // -------------------------------------------------------- CONSTRUCTORS, GETTERS AND SETTERS
    public User(Long userId, String firstName, String lastName, String email, String password, Photo photo, Role role, Set<Plant> plantsRegistered, Set<Alert> alertsCreated, Set<Alert> alertsResolved) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.photo = photo;
        this.role = role;
        this.plantsRegistered = plantsRegistered;
        this.alertsCreated = alertsCreated;
        this.alertsResolved = alertsResolved;
    }

    public User() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Plant> getPlantsRegistered() {
        return plantsRegistered;
    }

    public void setPlantsRegistered(Set<Plant> plantsRegistered) {
        this.plantsRegistered = plantsRegistered;
    }

    public Set<Alert> getAlertsCreated() {
        return alertsCreated;
    }

    public void setAlertsCreated(Set<Alert> alertsCreated) {
        this.alertsCreated = alertsCreated;
    }

    public Set<Alert> getAlertsResolved() {
        return alertsResolved;
    }

    public void setAlertsResolved(Set<Alert> alertsResolved) {
        this.alertsResolved = alertsResolved;
    }

    

}
