package com.example.automationservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "zone-service")
public interface ZoneClient {

    @GetMapping("/api/zones/{id}")
    ZoneDTO getZone(@PathVariable("id") Long id);
}