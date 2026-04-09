package com.example.sensorservice.client;

import com.example.sensorservice.dto.SensorDataDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class AutomationClient {

    private final RestTemplate restTemplate;

    public void sendToAutomation(SensorDataDTO data) {

        restTemplate.postForObject(
                "http://automation-service/api/automation/process",
                data,
                Void.class);
    }
}