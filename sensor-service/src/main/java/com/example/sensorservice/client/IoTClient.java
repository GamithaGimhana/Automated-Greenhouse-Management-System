package com.example.sensorservice.client;

import com.example.sensorservice.dto.TelemetryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class IoTClient {

    private final WebClient webClient;
    private final IoTAuthClient authClient;

    public TelemetryResponse getTelemetry(String deviceId) {

        return webClient.get()
                .uri("http://104.211.95.241:8080/api/devices/telemetry/" + deviceId)
                .header("Authorization", "Bearer " + authClient.getToken())
                .retrieve()
                .bodyToMono(TelemetryResponse.class)
                .block();
    }
}