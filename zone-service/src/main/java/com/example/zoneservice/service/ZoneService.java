package com.example.zoneservice.service;

import com.example.zoneservice.dto.ZoneRequestDTO;
import com.example.zoneservice.entity.Zone;

import java.util.List;

public interface ZoneService {

    Zone createZone(ZoneRequestDTO dto);

    Zone getZone(Long id);

    Zone updateZone(Long id, ZoneRequestDTO dto);

    void deleteZone(Long id);

    List<Zone> getAllZones();
}