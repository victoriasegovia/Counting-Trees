package com.countingTree.Counting.Tree.App.dto;

import java.util.Optional;

public class UserDTO {

    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String imageBase64;

    // -------------------------------------------------------- CONSTRUCTORS
    public UserDTO() {}

    public UserDTO(Long userId, String firstName, String lastName, String email, String role, String imageBase64) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.imageBase64 = imageBase64;
    }

    // -------------------------------------------------------- GETTERS Y SETTERS
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getImageBase64() { return imageBase64; }
    public void setImageBase64(String imageBase64) { this.imageBase64 = imageBase64; }

}
