package com.example.automationservice.dto;

import lombok.Data;

@Data
public class ZoneDTO {

    private Long id;
    private String name;
    private double minTemp;
    private double maxTemp;
    private String deviceId;
}