package com.example.zoneservice.client;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class IoTAuthClient {

    private final WebClient webClient = WebClient.create("http://104.211.95.241:8080/api");

    private String accessToken;

    public String getToken() {

        if (accessToken != null) return accessToken;

        AuthRequest request = new AuthRequest();
        request.setUsername("testuser");
        request.setPassword("123456");

        AuthResponse response = webClient.post()
                .uri("/auth/login")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(AuthResponse.class)
                .block();

        accessToken = response.getAccessToken();
        return accessToken;
    }

    @Data
    static class AuthRequest {
        private String username;
        private String password;
    }

    @Data
    static class AuthResponse {
        private String accessToken;
        private String refreshToken;
    }
}