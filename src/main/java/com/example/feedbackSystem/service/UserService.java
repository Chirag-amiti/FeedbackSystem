package com.example.feedbackSystem.service;

import com.example.feedbackSystem.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
    void deleteUser(Long id);
    User saveUser(User user);
}
