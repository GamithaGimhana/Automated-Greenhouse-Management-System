package com.example.automationservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "automation_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AutomationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String zoneId;

    private double temperature;

    private String action;

    private LocalDateTime timestamp;
}