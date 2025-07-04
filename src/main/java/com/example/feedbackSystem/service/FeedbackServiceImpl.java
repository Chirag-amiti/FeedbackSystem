package com.example.feedbackSystem.service;

import com.example.feedbackSystem.model.Feedback;
import com.example.feedbackSystem.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public Feedback submitFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public List<Feedback> getFeedbackForUser(Long userId) {
        return feedbackRepository.findByToUserId(userId);
    }

    @Override
    public List<Feedback> getFeedbackByUser(Long userId) {
        return feedbackRepository.findByFromUserId(userId);
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    @Override
    public List<Feedback> getFeedbackByTeam(String team) {
        return feedbackRepository.findAllFeedbacksByTeam(team);
    }

    @Override
    public Feedback updateFeedback(Long id, Feedback updatedFeedback) {
        Feedback existing = feedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback not found"));

        existing.setComments(updatedFeedback.getComments());
        existing.setRating(updatedFeedback.getRating());
        existing.setPerformanceCycle(updatedFeedback.getPerformanceCycle());

        return feedbackRepository.save(existing);
    }

    @Override
    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }

}
