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
    @JoinColumn(name = "from_user_id")
    private User fromUser;

    @ManyToOne
    @JoinColumn(name = "to_user_id")
    private User toUser;

    @ManyToOne
    @JoinColumn(name = "cycle_id")
    private PerformanceCycle performanceCycle;

    public Feedback() {
    }

    public Feedback(String comments, int rating, User fromUser, User toUser, PerformanceCycle performanceCycle) {
        this.comments = comments;
        this.rating = rating;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.performanceCycle = performanceCycle;
    }

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

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public PerformanceCycle getPerformanceCycle() {
        return performanceCycle;
    }

    public void setPerformanceCycle(PerformanceCycle performanceCycle) {
        this.performanceCycle = performanceCycle;
    }
}


/*package com.example.feedbackSystem.model;

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
    @JoinColumn(name = "from_user_id")
    private User fromUser;

    @ManyToOne
    @JoinColumn(name = "to_user_id")
    private User toUser;

    @ManyToOne
    @JoinColumn(name = "cycle_id")
    private PerformanceCycle performanceCycle;

    public Feedback() {
    }

    public Feedback(String comments, int rating, User fromUser, User toUser, PerformanceCycle performanceCycle) {
        this.comments = comments;
        this.rating = rating;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.performanceCycle = performanceCycle;
    }

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

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public PerformanceCycle getPerformanceCycle() {
        return performanceCycle;
    }

    public void setPerformanceCycle(PerformanceCycle performanceCycle) {
        this.performanceCycle = performanceCycle;
    }
}
 */
