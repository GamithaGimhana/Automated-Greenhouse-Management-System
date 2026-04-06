package com.example.zoneservice.service.impl;

import com.example.zoneservice.dto.ZoneRequestDTO;
import com.example.zoneservice.entity.Zone;
import com.example.zoneservice.repository.ZoneRepository;
import com.example.zoneservice.service.ZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ZoneServiceImpl implements ZoneService {

    private final ZoneRepository zoneRepository;

    @Override
    public Zone createZone(ZoneRequestDTO dto) {

        // validation
        if (dto.getMinTemp() >= dto.getMaxTemp()) {
            throw new RuntimeException("minTemp must be less than maxTemp");
        }

        // call IoT API
        String deviceId = "TEMP_DEVICE_ID"; // placeholder

        Zone zone = new Zone();
        zone.setName(dto.getName());
        zone.setMinTemp(dto.getMinTemp());
        zone.setMaxTemp(dto.getMaxTemp());
        zone.setDeviceId(deviceId);

        return zoneRepository.save(zone);
    }

    @Override
    public Zone getZone(Long id) {
        return zoneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Zone not found"));
    }

    @Override
    public Zone updateZone(Long id, ZoneRequestDTO dto) {

        Zone zone = getZone(id);

        if (dto.getMinTemp() >= dto.getMaxTemp()) {
            throw new RuntimeException("Invalid temperature range");
        }

        zone.setName(dto.getName());
        zone.setMinTemp(dto.getMinTemp());
        zone.setMaxTemp(dto.getMaxTemp());

        return zoneRepository.save(zone);
    }

    @Override
    public void deleteZone(Long id) {
        zoneRepository.deleteById(id);
    }

    @Override
    public List<Zone> getAllZones() {
        return zoneRepository.findAll();
    }
}