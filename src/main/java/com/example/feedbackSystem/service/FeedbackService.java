package com.example.feedbackSystem.service;

import com.example.feedbackSystem.model.Feedback;

import java.util.List;

public interface FeedbackService {
    Feedback submitFeedback(Feedback feedback);

    List<Feedback> getFeedbackForUser(Long userId); // Received feedback

    List<Feedback> getFeedbackByUser(Long userId); // Given feedback

    List<Feedback> getAllFeedbacks();

    List<Feedback> getFeedbackByTeam(String team);

    Feedback updateFeedback(Long id, Feedback updatedFeedback);

    void deleteFeedback(Long id);

}
