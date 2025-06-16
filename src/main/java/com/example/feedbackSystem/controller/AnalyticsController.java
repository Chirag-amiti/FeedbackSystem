package com.example.feedbackSystem.controller;

import com.example.feedbackSystem.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/average-rating/{team}")
    public ResponseEntity<Double> getAverageRating(@PathVariable String team) {
        return ResponseEntity.ok(analyticsService.getAverageRatingForTeam(team));
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/top-rated")
    public ResponseEntity<?> getTopRatedEmployees() {
        return ResponseEntity.ok(analyticsService.getTopRatedUsers());
    }
}
