package com.example.automationservice.service.impl;

import com.example.automationservice.client.ZoneClient;
import com.example.automationservice.dto.SensorDataDTO;
import com.example.automationservice.dto.ZoneDTO;
import com.example.automationservice.entity.AutomationLog;
import com.example.automationservice.repository.AutomationRepository;
import com.example.automationservice.service.AutomationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AutomationServiceImpl implements AutomationService {

    private final ZoneClient zoneClient;
    private final AutomationRepository repository;

    @Override
    public void processData(SensorDataDTO data) {

        Long zoneId = Long.parseLong(data.getZoneId());

        ZoneDTO zone = zoneClient.getZone(zoneId);

        double minTemp = zone.getMinTemp();
        double maxTemp = zone.getMaxTemp();

        String action = "NO_ACTION";

        if (data.getTemperature() > maxTemp) {
            action = "TURN_FAN_ON";
        } else if (data.getTemperature() < minTemp) {
            action = "TURN_HEATER_ON";
        }

        AutomationLog log = new AutomationLog();
        log.setZoneId(data.getZoneId());
        log.setTemperature(data.getTemperature());
        log.setAction(action);
        log.setTimestamp(LocalDateTime.now());

        repository.save(log);

        System.out.println("Zone " + zoneId + " → " + action);
    }
}