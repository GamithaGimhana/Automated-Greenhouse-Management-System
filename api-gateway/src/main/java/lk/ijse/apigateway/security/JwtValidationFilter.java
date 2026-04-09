package lk.ijse.apigateway.security;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import java.util.Map;

@Component
public class JwtValidationFilter implements GlobalFilter, Ordered {

    private static final String BEARER_PREFIX = "Bearer ";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getPath().value();

        // Only protect domain APIs exposed by the gateway.
        if (!path.startsWith("/api/")) {
            return chain.filter(exchange);
        }

        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith(BEARER_PREFIX)) {
            return unauthorized(exchange);
        }

        String token = authHeader.substring(BEARER_PREFIX.length()).trim();
        if (token.isEmpty() || !isJwtToken(token) || isExpired(token)) {
            return unauthorized(exchange);
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }

    private boolean isJwtToken(String token) {
        return token.split("\\.").length == 3;
    }

    private boolean isExpired(String token) {
        try {
            String[] parts = token.split("\\.");
            String payloadJson = decodeBase64Url(parts[1]);
            Map<String, Object> claims = objectMapper.readValue(payloadJson, new TypeReference<>() {
            });

            Object expClaim = claims.get("exp");
            if (expClaim == null) {
                return true;
            }

            long exp = ((Number) expClaim).longValue();
            return Instant.now().getEpochSecond() >= exp;
        } catch (Exception ex) {
            return true;
        }
    }

    private String decodeBase64Url(String value) {
        int padding = 4 - (value.length() % 4);
        if (padding > 0 && padding < 4) {
            value = value + "=".repeat(padding);
        }
        byte[] decoded = Base64.getUrlDecoder().decode(value);
        return new String(decoded, StandardCharsets.UTF_8);
    }

    private Mono<Void> unauthorized(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }
}
