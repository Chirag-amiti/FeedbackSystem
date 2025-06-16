package com.example.feedbackSystem.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "performance_cycles")
public class PerformanceCycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String quarterName; // e.g. - Q1 2025

    private LocalDate startDate;
    private LocalDate endDate;

    @OneToMany(mappedBy = "performanceCycle", cascade = CascadeType.ALL)
    private List<Feedback> feedbacks;

    public PerformanceCycle() {}

    public PerformanceCycle(String quarterName, LocalDate startDate, LocalDate endDate) {
        this.quarterName = quarterName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getQuarterName() {
        return quarterName;
    }

    public void setQuarterName(String quarterName) {
        this.quarterName = quarterName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }
}
