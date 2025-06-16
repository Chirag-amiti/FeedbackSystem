package com.example.feedbackSystem.repository;

import com.example.feedbackSystem.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByToUserId(Long toUserId);
    List<Feedback> findByFromUserId(Long fromUserId);

    // @Query("SELECT AVG(f.rating) FROM Feedback f WHERE f.toUser.team = :team")
    // Double findAverageRatingByTeam(String team);

    @Query("SELECT f.toUser.id, AVG(f.rating) as avgRating FROM Feedback f GROUP BY f.toUser.id ORDER BY avgRating DESC LIMIT 5")
    List<Object[]> findTopRatedUsers();

     @Query("SELECT AVG(f.rating) FROM Feedback f WHERE f.toUser.team = :team")
    Double findAverageRatingByTeam(@Param("team") String team);
}
