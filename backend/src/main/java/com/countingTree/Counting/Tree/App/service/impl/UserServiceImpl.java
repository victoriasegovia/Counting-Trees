package com.countingTree.Counting.Tree.App.service.impl;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.countingTree.Counting.Tree.App.dto.UserDTO;
import com.countingTree.Counting.Tree.App.model.AlertType;
import com.countingTree.Counting.Tree.App.model.User;
import com.countingTree.Counting.Tree.App.repository.UserRepository;
import com.countingTree.Counting.Tree.App.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + userId + " not found."));
        return mapToDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public void addUser(User newUser) {
        validateUser(newUser);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.save(newUser);
    }

    @Override
    public UserDTO updateUser(Long userId, User user) {
        validateUser(user);
        User userUpdated = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + userId + " not found."));

        if (user.getFirstName() != null && !user.getFirstName().trim().isEmpty()) {
            userUpdated.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null && !user.getLastName().trim().isEmpty()) {
            userUpdated.setLastName(user.getLastName());
        }
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            userUpdated.setPassword(user.getPassword()); // Note: should probably be encoded
        }

        // Collections and relationships
        if (user.getPhoto() != null) {
            userUpdated.setPhoto(user.getPhoto());
        }
        if (user.getPlantsRegistered() != null) {
            userUpdated.setPlantsRegistered(user.getPlantsRegistered());
        }
        if (user.getAlertsCreated() != null) {
            userUpdated.setAlertsCreated(user.getAlertsCreated());
        }
        if (user.getAlertsResolved() != null) {
            userUpdated.setAlertsResolved(user.getAlertsResolved());
        }

        userRepository.save(userUpdated);

        return mapToDTO(userUpdated);
    }

    @Override
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User with ID " + userId + " not found.");
        }
        userRepository.deleteById(userId);
    }

    // -------------------------- EXTRA METHODS
    private void validateUser(User user) {

        if (user == null) {
            throw new IllegalArgumentException("User must not be null");
        }
        if (user.getFirstName() == null || user.getFirstName().trim().isEmpty()) {
            throw new IllegalArgumentException("First name must not be null or empty");
        }
        if (user.getLastName() == null || user.getLastName().trim().isEmpty()) {
            throw new IllegalArgumentException("Last name must not be null or empty");
        }
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email must not be null or empty");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password must not be null or empty");
        }
        if (user.getRole() == null) {
            throw new IllegalArgumentException("Role must not be null or empty");
        }

        User existing = userRepository.findByEmail(user.getEmail());

        if (existing != null && !existing.getUserId().equals(user.getUserId())) {
            throw new IllegalArgumentException("User email already exists.");
        }

    }

    private UserDTO mapToDTO(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setUserId(user.getUserId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole().toString());
        if (user.getPhoto() != null) {
            userDTO.setImageBase64(Base64.getEncoder().encodeToString(user.getPhoto().getImageData()));
        }
        return userDTO;
    }
}
