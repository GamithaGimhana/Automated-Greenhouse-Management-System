package com.example.sensorservice.scheduler;

import com.example.sensorservice.client.AutomationClient;
import com.example.sensorservice.client.IoTClient;
import com.example.sensorservice.controller.SensorController;
import com.example.sensorservice.dto.SensorDataDTO;
import com.example.sensorservice.dto.TelemetryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SensorScheduler {

    private final IoTClient ioTClient;
    private final AutomationClient automationClient;
    private final SensorController controller;

    private final String DEVICE_ID = "PUT_REAL_DEVICE_ID";

    @Scheduled(fixedRate = 10000)
    public void fetchAndSendData() {

        System.out.println("Fetching REAL IoT data...");

        TelemetryResponse response = ioTClient.getTelemetry(DEVICE_ID);

        SensorDataDTO data = new SensorDataDTO();
        data.setZoneId(response.getZoneId());
        data.setTemperature(response.getValue().getTemperature());
        data.setHumidity(response.getValue().getHumidity());

        controller.setLastData(data);

        automationClient.sendToAutomation(data);

        System.out.println("Sent real data: " + data.getTemperature());
    }
}