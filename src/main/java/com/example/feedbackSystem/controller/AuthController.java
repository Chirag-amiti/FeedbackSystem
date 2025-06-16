package com.example.feedbackSystem.controller;

import com.example.feedbackSystem.model.User;
import com.example.feedbackSystem.model.Role;
import com.example.feedbackSystem.repository.UserRepository;
import com.example.feedbackSystem.security.JwtUtils;
import com.example.feedbackSystem.service.CustomUserDetails;
import com.example.feedbackSystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    // ---------------- Register ----------------
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // default role as EMPLOYEE if none set
        if (user.getRole() == null) {
            user.setRole(Role.EMPLOYEE);
        }

        User saved = userService.saveUser(user);

        return ResponseEntity.ok(saved);
    }

    // ---------------- Login ----------------
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        try {
            Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.get("email"),
                    loginRequest.get("password")
                )
            );

            CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
            String token = jwtUtils.generateToken(userDetails.getUsername());

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("role", userDetails.getUserEntity().getRole());

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}


