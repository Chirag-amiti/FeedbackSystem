package com.example.feedbackSystem.controller;

import com.example.feedbackSystem.model.Feedback;
import com.example.feedbackSystem.model.User;
import com.example.feedbackSystem.repository.UserRepository;
import com.example.feedbackSystem.service.CustomUserDetails;
import com.example.feedbackSystem.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private UserRepository userRepository;

    // Submit feedback (only EMPLOYEE)
    @PostMapping("/submit/{toUserId}")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<Feedback> submitFeedback(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long toUserId,
            @RequestBody Feedback feedback
    ) {
        User fromUser = userDetails.getUserEntity();
        User toUser = userRepository.findById(toUserId).orElseThrow();

        feedback.setFromUser(fromUser);
        feedback.setToUser(toUser);

        return ResponseEntity.ok(feedbackService.submitFeedback(feedback));
    }

    // View feedbacks received by logged-in user
    @GetMapping("/received")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<List<Feedback>> getMyReceivedFeedbacks(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(feedbackService.getFeedbackForUser(userDetails.getUserEntity().getId()));
    }

    // View feedbacks given by logged-in user
    @GetMapping("/given")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<List<Feedback>> getMyGivenFeedbacks(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(feedbackService.getFeedbackByUser(userDetails.getUserEntity().getId()));
    }

    // Manager: view all feedbacks
    @GetMapping("/all")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<List<Feedback>> getAllFeedbacks() {
        return ResponseEntity.ok(feedbackService.getAllFeedbacks());
    }
}
