package com.example.zoneservice.controller;

import com.example.zoneservice.dto.ZoneRequestDTO;
import com.example.zoneservice.entity.Zone;
import com.example.zoneservice.service.ZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zones")
@RequiredArgsConstructor
public class ZoneController {

    private final ZoneService zoneService;

    @PostMapping
    public Zone createZone(@RequestBody ZoneRequestDTO dto) {
        return zoneService.createZone(dto);
    }

    @GetMapping("/{id}")
    public Zone getZone(@PathVariable Long id) {
        return zoneService.getZone(id);
    }

    @GetMapping
    public List<Zone> getAllZones() {
        return zoneService.getAllZones();
    }

    @PutMapping("/{id}")
    public Zone updateZone(@PathVariable Long id,
                           @RequestBody ZoneRequestDTO dto) {
        return zoneService.updateZone(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteZone(@PathVariable Long id) {
        zoneService.deleteZone(id);
    }
}