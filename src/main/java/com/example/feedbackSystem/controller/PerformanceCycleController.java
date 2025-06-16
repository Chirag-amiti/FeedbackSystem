package com.example.feedbackSystem.controller;

import com.example.feedbackSystem.model.PerformanceCycle;
import com.example.feedbackSystem.service.PerformanceCycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cycles")
public class PerformanceCycleController {

    @Autowired
    private PerformanceCycleService cycleService;

    @PostMapping("/create")
    public ResponseEntity<PerformanceCycle> createCycle(@RequestBody PerformanceCycle cycle) {
        return ResponseEntity.ok(cycleService.createCycle(cycle));
    }

    @GetMapping
    public ResponseEntity<List<PerformanceCycle>> getAllCycles() {
        return ResponseEntity.ok(cycleService.getAllCycles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerformanceCycle> getCycleById(@PathVariable Long id) {
        return ResponseEntity.ok(cycleService.getCycleById(id));
    }
}
