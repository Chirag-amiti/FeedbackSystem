package com.example.feedbackSystem.controller;

import com.example.feedbackSystem.model.User;
import com.example.feedbackSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*") // allow frontend requests for now
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
    @PreAuthorize("hasRole('MANAGER')")
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
}

