package com.example.sensorservice.scheduler;

import com.example.sensorservice.client.AutomationClient;
import com.example.sensorservice.client.IoTClient;
import com.example.sensorservice.client.ZoneClient;
import com.example.sensorservice.controller.SensorController;
import com.example.sensorservice.dto.SensorDataDTO;
import com.example.sensorservice.dto.TelemetryResponse;
import com.example.sensorservice.dto.ZoneDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SensorScheduler {

    private final IoTClient ioTClient;
    private final AutomationClient automationClient;
    private final ZoneClient zoneClient;
    private final SensorController controller;

    @Scheduled(fixedRate = 10000)
    public void fetchAndSendData() {

        System.out.println("Fetching REAL sensor data for ALL zones...");

        List<ZoneDTO> zones = zoneClient.getAllZones();

        for (ZoneDTO zone : zones) {

            if (zone.getDeviceId() == null || zone.getDeviceId().startsWith("TEMP")) {
                System.out.println("Skipping zone " + zone.getId() + " (invalid deviceId)");
                continue;
            }

            try {
                TelemetryResponse response =
                        ioTClient.getTelemetry(zone.getDeviceId());

                SensorDataDTO data = new SensorDataDTO();
                data.setZoneId(zone.getId().toString());
                data.setTemperature(response.getValue().getTemperature());
                data.setHumidity(response.getValue().getHumidity());

                controller.setLastData(data);

                automationClient.sendToAutomation(data);

                System.out.println("Zone " + zone.getId() +
                        " Temp: " + data.getTemperature());

            } catch (Exception e) {
                System.out.println("Error processing zone: " + zone.getId());
            }
        }
    }
}