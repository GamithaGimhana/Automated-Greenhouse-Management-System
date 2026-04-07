package com.example.automationservice.service;

import com.example.automationservice.dto.SensorDataDTO;

public interface AutomationService {
    void processData(SensorDataDTO data);
}