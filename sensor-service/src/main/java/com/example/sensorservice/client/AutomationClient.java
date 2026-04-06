package com.example.sensorservice.client;

import com.example.sensorservice.dto.SensorDataDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class AutomationClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public void sendToAutomation(SensorDataDTO data) {
        restTemplate.postForObject(
                "http://localhost:8083/api/automation/process",
                data,
                Void.class
        );
    }
}