package com.example.zoneservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Zone {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private double minTemp;
    private double maxTemp;

    private String deviceId;
}