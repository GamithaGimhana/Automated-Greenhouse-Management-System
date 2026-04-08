package com.example.sensorservice.dto;

import lombok.Data;

@Data
public class TelemetryResponse {

    private String deviceId;
    private String zoneId;
    private Value value;

    @Data
    public static class Value {
        private double temperature;
        private double humidity;
    }
}