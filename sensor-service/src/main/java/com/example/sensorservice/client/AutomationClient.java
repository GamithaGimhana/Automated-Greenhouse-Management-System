package com.example.sensorservice.client;

import com.example.sensorservice.dto.SensorDataDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AutomationClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public void sendToAutomation(SensorDataDTO data) {

        restTemplate.postForObject(
                "http://AUTOMATION-SERVICE/api/automation/process",
                data,
                Void.class
        );
    }
}