package com.example.sensorservice.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class IoTClient {

    private final WebClient webClient;

    public String getTelemetry(String deviceId, String token) {

        return webClient.get()
                .uri("http://104.211.95.241:8080/api/devices/telemetry/" + deviceId)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}