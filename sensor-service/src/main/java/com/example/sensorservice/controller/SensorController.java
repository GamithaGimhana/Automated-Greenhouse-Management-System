package com.example.sensorservice.controller;

import com.example.sensorservice.dto.SensorDataDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sensors")
public class SensorController {

    private SensorDataDTO lastData;

    @GetMapping("/latest")
    public SensorDataDTO getLatest() {
        return lastData;
    }

    public void setLastData(SensorDataDTO data) {
        this.lastData = data;
    }
}