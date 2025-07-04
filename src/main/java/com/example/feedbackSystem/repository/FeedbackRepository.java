// package com.example.feedbackSystem.repository;

// import com.example.feedbackSystem.model.Feedback;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;

// import java.util.List;

// public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
//     List<Feedback> findByToUserId(Long toUserId);

//     List<Feedback> findByFromUserId(Long fromUserId);

//     // @Query("SELECT AVG(f.rating) FROM Feedback f WHERE f.toUser.team = :team")
//     // Double findAverageRatingByTeam(String team);

//     @Query("SELECT f.toUser.id, AVG(f.rating) as avgRating FROM Feedback f GROUP BY f.toUser.id ORDER BY avgRating DESC LIMIT 5")
//     List<Object[]> findTopRatedUsers();

//     @Query("SELECT AVG(f.rating) FROM Feedback f WHERE f.toUser.team = :team")
//     Double findAverageRatingByTeam(@Param("team") String team);

//     @Query("SELECT f.performanceCycle.title, AVG(f.rating) FROM Feedback f WHERE f.performanceCycle IS NOT NULL GROUP BY f.performanceCycle.title ORDER BY f.performanceCycle.startDate")
//     List<Object[]> findAverageRatingGroupedByCycle();

// }

package com.example.feedbackSystem.repository;

import com.example.feedbackSystem.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByToUserId(Long toUserId);

    List<Feedback> findByFromUserId(Long fromUserId);

    @Query("SELECT f FROM Feedback f WHERE f.fromUser.team = :team OR f.toUser.team = :team")
    List<Feedback> findAllFeedbacksByTeam(@Param("team") String team);

    @Query("SELECT f.toUser.id, AVG(f.rating) as avgRating FROM Feedback f GROUP BY f.toUser.id ORDER BY avgRating DESC LIMIT 5")
    List<Object[]> findTopRatedUsers();

    @Query("SELECT AVG(f.rating) FROM Feedback f WHERE f.toUser.team = :team")
    Double findAverageRatingByTeam(@Param("team") String team);

    // --- START OF UPDATED QUERY ---
    // The previous query caused a SQL error (1055, 42000) because
    // 'f.performanceCycle.startDate'
    // was in the ORDER BY clause but not in the GROUP BY clause, which violates
    // MySQL's
    // ONLY_FULL_GROUP_BY SQL mode.
    //
    // Solution: Add f.performanceCycle.startDate to the GROUP BY clause.
    // This ensures that each unique combination of title and start date forms a
    // group,
    // allowing ordering by start date without ambiguity.
    @Query("SELECT f.performanceCycle.title, AVG(f.rating) FROM Feedback f WHERE f.performanceCycle IS NOT NULL GROUP BY f.performanceCycle.title, f.performanceCycle.startDate ORDER BY f.performanceCycle.startDate")
    List<Object[]> findAverageRatingGroupedByCycle();
    // --- END OF UPDATED QUERY ---
}
