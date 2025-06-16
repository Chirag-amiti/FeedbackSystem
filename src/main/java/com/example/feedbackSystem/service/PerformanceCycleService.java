package com.example.feedbackSystem.service;

import com.example.feedbackSystem.model.PerformanceCycle;
import java.util.List;

public interface PerformanceCycleService {
    PerformanceCycle createCycle(PerformanceCycle cycle);
    List<PerformanceCycle> getAllCycles();
    PerformanceCycle getCycleById(Long id);
}
