package com.example.automationservice.controller;

import com.example.automationservice.dto.SensorDataDTO;
import com.example.automationservice.entity.AutomationLog;
import com.example.automationservice.repository.AutomationRepository;
import com.example.automationservice.service.AutomationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/automation")
@RequiredArgsConstructor
public class AutomationController {

    private final AutomationService automationService;
    private final AutomationRepository repository;

    @PostMapping("/process")
    public void process(@RequestBody SensorDataDTO data) {
        automationService.processData(data);
    }

    @GetMapping("/logs")
    public List<AutomationLog> getLogs() {
        return repository.findAll();
    }
}