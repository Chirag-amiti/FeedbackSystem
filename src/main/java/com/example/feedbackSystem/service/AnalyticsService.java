package com.example.feedbackSystem.service;

import java.util.List;
import java.util.Map;

public interface AnalyticsService {
    Double getAverageRatingForTeam(String team);
    List<Map<String, Object>> getTopRatedUsers();
    Map<String, Double> getAverageRatingByCycle();

}
