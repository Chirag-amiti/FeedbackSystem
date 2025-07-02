package com.example.feedbackSystem.service;

import com.example.feedbackSystem.model.User;
import com.example.feedbackSystem.repository.FeedbackRepository;
import com.example.feedbackSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AnalyticsServiceImpl implements AnalyticsService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Map<String, Double> getAverageRatingByCycle() {
        List<Object[]> results = feedbackRepository.findAverageRatingGroupedByCycle();
        Map<String, Double> cycleRatings = new LinkedHashMap<>();

        for (Object[] row : results) {
            String title = (String) row[0];
            Double avg = (Double) row[1];
            cycleRatings.put(title, avg);
        }
        return cycleRatings;
    }

    @Override
    public Double getAverageRatingForTeam(String team) {
        return feedbackRepository.findAverageRatingByTeam(team);
    }

    @Override
    public List<Map<String, Object>> getTopRatedUsers() {
        List<Object[]> data = feedbackRepository.findTopRatedUsers();
        List<Map<String, Object>> result = new ArrayList<>();

        for (Object[] row : data) {
            Long userId = (Long) row[0];
            Double avgRating = (Double) row[1];
            Optional<User> userOpt = userRepository.findById(userId);

            userOpt.ifPresent(user -> {
                Map<String, Object> map = new HashMap<>();
                map.put("user", user.getName());
                map.put("avgRating", avgRating);
                result.add(map);
            });
        }

        return result;
    }
}
