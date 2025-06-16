package com.example.feedbackSystem.repository;

import com.example.feedbackSystem.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    // Later I will add custom queries here
}

