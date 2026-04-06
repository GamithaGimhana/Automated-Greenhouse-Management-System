package com.example.sensorservice.scheduler;

import com.example.sensorservice.client.AutomationClient;
import com.example.sensorservice.client.IoTClient;
import com.example.sensorservice.controller.SensorController;
import com.example.sensorservice.dto.SensorDataDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SensorScheduler {

    private final IoTClient ioTClient;
    private final AutomationClient automationClient;
    private final SensorController sensorController;

    @Scheduled(fixedRate = 10000)
    public void fetchAndSendData() {

        System.out.println("Fetching sensor data...");

        // temp values
        SensorDataDTO data = new SensorDataDTO();
        data.setZoneId("Zone-A");
        data.setTemperature(28.5);
        data.setHumidity(55.0);

        // save latest for debug endpoint
        sensorController.setLastData(data);

        // send to automation service
        automationClient.sendToAutomation(data);

        System.out.println("Data sent to automation service");
    }
}