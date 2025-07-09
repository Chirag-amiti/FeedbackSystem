package com.example.feedbackSystem.repository;

import com.example.feedbackSystem.model.Role;
import com.example.feedbackSystem.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    Optional<User> findByEmail(String email);
    Page<User> findByRole(Role role, Pageable pageable);
}
// public interface UserRepository extends JpaRepository<User, Long> {
//     Optional<User> findByEmail(String email);
//     Page<User> findByRole(Role role, Pageable pageable);
// }
