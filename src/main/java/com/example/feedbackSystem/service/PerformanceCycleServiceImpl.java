package com.example.feedbackSystem.service;

import com.example.feedbackSystem.model.PerformanceCycle;
import com.example.feedbackSystem.repository.PerformanceCycleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerformanceCycleServiceImpl implements PerformanceCycleService {

    @Autowired
    private PerformanceCycleRepository cycleRepository;

    @Override
    public PerformanceCycle createCycle(PerformanceCycle cycle) {
        return cycleRepository.save(cycle);
    }

    @Override
    public List<PerformanceCycle> getAllCycles() {
        return cycleRepository.findAll();
    }

    @Override
    public PerformanceCycle getCycleById(Long id) {
        return cycleRepository.findById(id).orElse(null);
    }
}
