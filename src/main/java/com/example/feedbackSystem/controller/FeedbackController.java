package com.example.feedbackSystem.controller;

import java.util.Map; // ✅ Required import

import com.example.feedbackSystem.repository.PerformanceCycleRepository; // ✅ Required import

import com.example.feedbackSystem.model.Feedback;
import com.example.feedbackSystem.model.PerformanceCycle;
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

    @Autowired
    private PerformanceCycleRepository performanceCycleRepository;

    // Submit feedback (only EMPLOYEE)
    @PostMapping("/submit/{toUserId}")
    @PreAuthorize("hasAnyRole('EMPLOYEE', 'MANAGER')")
    public ResponseEntity<Feedback> submitFeedback(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long toUserId,
            @RequestBody Feedback feedback) {
        User fromUser = userDetails.getUserEntity();
        User toUser = userRepository.findById(toUserId).orElseThrow();

        feedback.setFromUser(fromUser);
        feedback.setToUser(toUser);

        return ResponseEntity.ok(feedbackService.submitFeedback(feedback));
    }

    // View feedbacks received by logged-in user
    @GetMapping("/received")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<List<Feedback>> getMyReceivedFeedbacks(
            @AuthenticationPrincipal CustomUserDetails userDetails) {
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

    // Team feedback view (Manager only)
    @GetMapping("/team")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<List<Feedback>> getTeamFeedback(@AuthenticationPrincipal CustomUserDetails userDetails) {
        String managerTeam = userDetails.getUserEntity().getTeam();
        return ResponseEntity.ok(feedbackService.getFeedbackByTeam(managerTeam));
    }

    // Delete feedback (Manager only)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
        return ResponseEntity.ok().build();
    }

    // Update feedback (Manager only)
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Feedback> updateFeedback(
            @PathVariable Long id,
            @RequestBody Map<String, Object> payload) {
        Feedback updated = new Feedback();
        updated.setComments((String) payload.get("comments"));
        updated.setRating((Integer) payload.get("rating"));

        if (payload.containsKey("cycleId") && payload.get("cycleId") != null) {
            Long cycleId = Long.parseLong(payload.get("cycleId").toString());
            PerformanceCycle cycle = performanceCycleRepository.findById(cycleId).orElseThrow();
            updated.setPerformanceCycle(cycle);
        }

        return ResponseEntity.ok(feedbackService.updateFeedback(id, updated));
    }

    @PostMapping("/manager/submit/{toUserId}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Feedback> managerSubmitFeedback(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long toUserId,
            @RequestBody Map<String, Object> payload) {
        User fromUser = userDetails.getUserEntity();
        User toUser = userRepository.findById(toUserId).orElseThrow();

        Feedback feedback = new Feedback();
        feedback.setFromUser(fromUser);
        feedback.setToUser(toUser);
        feedback.setComments((String) payload.get("comments"));
        feedback.setRating((Integer) payload.get("rating"));

        if (payload.containsKey("cycleId")) {
            String cycleIdStr = payload.get("cycleId").toString(); // ✅ safe even if frontend sends number or string
            Long cycleId = Long.parseLong(cycleIdStr);
            PerformanceCycle cycle = performanceCycleRepository.findById(cycleId).orElseThrow();
            feedback.setPerformanceCycle(cycle);
        }

        return ResponseEntity.ok(feedbackService.submitFeedback(feedback));
    }

}
