package com.example.feedbackSystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "feedbacks")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comments;
    private int rating;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;

    @ManyToOne
    @JoinColumn(name = "cycle_id")
    private PerformanceCycle performanceCycle;

    public Feedback() {}

    public Feedback(String comments, int rating, User sender, User receiver, PerformanceCycle performanceCycle) {
        this.comments = comments;
        this.rating = rating;
        this.sender = sender;
        this.receiver = receiver;
        this.performanceCycle = performanceCycle;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public PerformanceCycle getPerformanceCycle() {
        return performanceCycle;
    }

    public void setPerformanceCycle(PerformanceCycle performanceCycle) {
        this.performanceCycle = performanceCycle;
    }
}
