// package com.example.feedbackSystem.repository;

// import com.example.feedbackSystem.model.User;
// import org.springframework.data.jpa.repository.JpaRepository;

// public interface UserRepository extends JpaRepository<User, Long> {
//     User findByEmail(String email);
// }

package com.example.feedbackSystem.repository;

import com.example.feedbackSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}




