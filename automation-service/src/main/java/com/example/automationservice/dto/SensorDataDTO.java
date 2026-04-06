package com.example.automationservice.dto;

import lombok.Data;

@Data
public class SensorDataDTO {

    private String zoneId;
    private double temperature;
    private double humidity;
}