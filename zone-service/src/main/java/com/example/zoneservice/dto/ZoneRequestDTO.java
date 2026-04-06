package com.example.zoneservice.dto;

import lombok.Data;

@Data
public class ZoneRequestDTO {
    private String name;
    private double minTemp;
    private double maxTemp;
}