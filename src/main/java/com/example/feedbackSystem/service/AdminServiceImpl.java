package com.example.feedbackSystem.service;

import com.example.feedbackSystem.model.User;
import com.example.feedbackSystem.model.Role;
import com.example.feedbackSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<User> getUsersByRole(String role, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        try {
            Role parsedRole = Role.valueOf(role.toUpperCase());
            return userRepository.findAll((root, query, cb) -> cb.equal(root.get("role"), parsedRole), pageable);
        } catch (IllegalArgumentException e) {
            return Page.empty();
        }
    }

    @Override
    public Page<User> getAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        return userRepository.findAll(pageable);
    }

    @Override
    public User updateUserRole(Long userId, Role newRole) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setRole(newRole);
        return userRepository.save(user);
    }
}
