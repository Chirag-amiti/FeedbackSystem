package com.example.feedbackSystem.controller;

import com.example.feedbackSystem.model.User;
import com.example.feedbackSystem.service.UserService;
import com.example.feedbackSystem.service.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*") // allow frontend requests for now
// @CrossOrigin(origins = "http://localhost:3333")
public class UserController {

    @Autowired
    private UserService userService;

    // POST - Create user
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // GET - All users
    @GetMapping
    @PreAuthorize("hasAnyRole('EMPLOYEE', 'MANAGER')")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // GET - User by ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('MANAGER', 'EMPLOYEE')")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // DELETE - User by ID
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    // ✅ NEW: GET current logged-in user using token
    @GetMapping("/me")
    @PreAuthorize("hasAnyRole('EMPLOYEE', 'MANAGER')")
    public Map<String, Object> getCurrentUser(Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = userDetails.getUserEntity();

        // System.out.println("mai yaha tak aa  gaya huu.........");

        Map<String, Object> response = new HashMap<>();
        response.put("id", user.getId());
        response.put("name", user.getName());
        response.put("email", user.getEmail());
        response.put("role", user.getRole());
        response.put("team", user.getTeam());

        return response;
    }
}
