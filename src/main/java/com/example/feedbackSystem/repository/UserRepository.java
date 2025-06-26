package com.example.feedbackSystem.repository;

import com.example.feedbackSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// import java.util.UUID; // add extra

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}

// @Repository
// public interface UserRepository extends JpaRepository<User, UUID> {
//     Optional<User> findByEmail(String email);
// }




