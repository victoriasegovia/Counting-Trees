package com.countingTree.Counting.Tree.App.controller;

import com.countingTree.Counting.Tree.App.dto.UserDTO;
import com.countingTree.Counting.Tree.App.model.User;
import com.countingTree.Counting.Tree.App.service.UserService;
import com.countingTree.Counting.Tree.App.service.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<Void> addUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody User user) {
        UserDTO updated = userService.updateUser(id, user);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    // ------------------------------------------------------------ AUTH

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome, this endpoint is not secure";
    }

    @GetMapping("/guardian/guardianProfile")
    @PreAuthorize("hasRole('GUARDIAN')") // Use hasRole for role-based access control
    public String guardianProfile(@AuthenticationPrincipal UserDetailsImpl user) {
        return "Welcome to Guardian Profile " + user.getFullName() + ".";
    }

    @GetMapping("/botanist/botanistProfile")
    @PreAuthorize("hasRole('BOTANIST')") // Use hasRole for role-based access control
    public String botanistProfile(@AuthenticationPrincipal UserDetailsImpl user) {
        return "Welcome to Botanist Profile " + user.getFullName() + ".";
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasRole('ADMIN')") // Use hasRole for role-based access control
    public String adminProfile(@AuthenticationPrincipal UserDetailsImpl user) {
        return "Welcome to Admin Profile " + user.getFullName() + ".";
    }

}
