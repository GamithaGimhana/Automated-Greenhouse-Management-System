package com.example.zoneservice.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class IoTClient {

    private final WebClient webClient = WebClient.create("http://104.211.95.241:8080/api");

    public String createDevice(String token, String zoneId) {

        String response = webClient.post()
                .uri("http://104.211.95.241:8080/api/devices")
                .header("Authorization", "Bearer " + token)
                .bodyValue("""
                {
                  "name": "Sensor",
                  "zoneId": "%s"
                }
                """.formatted(zoneId))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return "PARSE_DEVICE_ID";
    }
}