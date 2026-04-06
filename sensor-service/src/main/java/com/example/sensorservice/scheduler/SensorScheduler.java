package com.example.sensorservice.scheduler;

import com.example.sensorservice.client.AutomationClient;
import com.example.sensorservice.client.IoTClient;
import com.example.sensorservice.dto.SensorDataDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SensorScheduler {

    private final IoTClient ioTClient;
    private final AutomationClient automationClient;

    @Scheduled(fixedRate = 10000)
    public void fetchAndSendData() {

        System.out.println("Fetching sensor data...");

        // temp values
        SensorDataDTO data = new SensorDataDTO();
        data.setZoneId("Zone-A");
        data.setTemperature(30.5);
        data.setHumidity(60.0);

        automationClient.sendToAutomation(data);
    }
}