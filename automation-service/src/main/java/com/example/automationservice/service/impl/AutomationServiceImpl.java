package com.example.automationservice.service.impl;

import com.example.automationservice.client.ZoneClient;
import com.example.automationservice.dto.SensorDataDTO;
import com.example.automationservice.entity.AutomationLog;
import com.example.automationservice.repository.AutomationRepository;
import com.example.automationservice.service.AutomationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AutomationServiceImpl implements AutomationService {

    private final ZoneClient zoneClient;
    private final AutomationRepository repository;

    @Override
    public void processData(SensorDataDTO data) {

        // fetch zone thresholds
        Map zone = (Map) zoneClient.getZone(1L); // temp

        double minTemp = Double.parseDouble(zone.get("minTemp").toString());
        double maxTemp = Double.parseDouble(zone.get("maxTemp").toString());

        String action = "NO_ACTION";

        // rule engine
        if (data.getTemperature() > maxTemp) {
            action = "TURN_FAN_ON";
        } else if (data.getTemperature() < minTemp) {
            action = "TURN_HEATER_ON";
        }

        // save log
        AutomationLog log = new AutomationLog();
        log.setZoneId(data.getZoneId());
        log.setTemperature(data.getTemperature());
        log.setAction(action);
        log.setTimestamp(LocalDateTime.now());

        repository.save(log);

        System.out.println("Action decided: " + action);
    }
}