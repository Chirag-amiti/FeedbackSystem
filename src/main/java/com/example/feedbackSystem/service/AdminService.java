package com.example.feedbackSystem.service;

import com.example.feedbackSystem.model.User;
import com.example.feedbackSystem.model.Role;
import org.springframework.data.domain.Page;

public interface AdminService {
    Page<User> getUsersByRole(String role, int page, int size);
    Page<User> getAllUsers(int page, int size);
    User updateUserRole(Long userId, Role newRole);
}
