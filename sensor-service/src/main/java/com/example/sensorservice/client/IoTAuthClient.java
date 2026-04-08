package com.example.sensorservice.client;

import com.example.sensorservice.dto.AuthRequest;
import com.example.sensorservice.dto.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class IoTAuthClient {

    private final WebClient webClient;

    private String accessToken;

    public String login() {

        AuthRequest request = new AuthRequest();
        request.setUsername("testuser");
        request.setPassword("123456");

        AuthResponse response = webClient.post()
                .uri("http://104.211.95.241:8080/api/auth/login")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(AuthResponse.class)
                .block();

        this.accessToken = response.getAccessToken();

        return accessToken;
    }

    public String getToken() {
        if (accessToken == null) {
            return login();
        }
        return accessToken;
    }
}