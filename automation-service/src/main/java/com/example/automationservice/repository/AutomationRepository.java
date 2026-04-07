package com.example.automationservice.repository;

import com.example.automationservice.entity.AutomationLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutomationRepository extends JpaRepository<AutomationLog, Long> {
}