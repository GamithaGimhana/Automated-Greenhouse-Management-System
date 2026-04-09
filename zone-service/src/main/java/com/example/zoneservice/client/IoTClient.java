package com.example.zoneservice.client;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class IoTClient {

    private final IoTAuthClient authClient;

    private final WebClient webClient = WebClient.create("http://104.211.95.241:8080/api");

    public String createDevice(String zoneId) {

        DeviceRequest request = new DeviceRequest();
        request.setName("Sensor-" + zoneId);
        request.setZoneId(zoneId);

        DeviceResponse response = webClient.post()
                .uri("/devices")
                .header("Authorization", "Bearer " + authClient.getToken())
                .bodyValue(request)
                .retrieve()
                .bodyToMono(DeviceResponse.class)
                .block();

        return response.getDeviceId();
    }

    @Data
    static class DeviceRequest {
        private String name;
        private String zoneId;
    }

    @Data
    static class DeviceResponse {
        private String deviceId;
    }
}