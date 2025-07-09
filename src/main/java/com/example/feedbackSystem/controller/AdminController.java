package com.example.feedbackSystem.controller;

import com.example.feedbackSystem.model.Role;
import com.example.feedbackSystem.model.User;
import com.example.feedbackSystem.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/users")
    public ResponseEntity<Map<String, Object>> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) Role role) {

        Page<User> userPage;

        if (role != null) {
            userPage = adminService.getUsersByRole(role.name(), page, size);
        } else {
            userPage = adminService.getAllUsers(page, size);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("users", userPage.getContent());
        response.put("currentPage", userPage.getNumber());
        response.put("totalPages", userPage.getTotalPages());
        response.put("totalItems", userPage.getTotalElements());

        return ResponseEntity.ok(response);
    }

    @PutMapping("/users/{userId}/role")
    public ResponseEntity<?> updateUserRole(@PathVariable Long userId, @RequestBody Map<String, String> request) {
        try {
            Role newRole = Role.valueOf(request.get("role"));
            User updatedUser = adminService.updateUserRole(userId, newRole);
            return ResponseEntity.ok(updatedUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid role value");
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
