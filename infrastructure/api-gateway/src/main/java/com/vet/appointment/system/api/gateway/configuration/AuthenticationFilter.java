package com.vet.appointment.system.api.gateway.configuration;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
public class AuthenticationFilter implements GatewayFilter {

    private final RouterValidator routerValidator;
    private final JwtUtil jwtUtil;

    public AuthenticationFilter(RouterValidator routerValidator, JwtUtil jwtUtil) {
        this.routerValidator = routerValidator;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        if(routerValidator.isSecured.test(request)) {
            log.info("Requesting protected resource");
            if(!request.getHeaders().containsKey("Authorization")) {
                return onError(exchange, HttpStatus.UNAUTHORIZED);
            }
            final String token = request.getHeaders().getOrEmpty("Authorization").get(0).substring(7);
            if(!jwtUtil.isTokenValid(token)) {
                return onError(exchange, HttpStatus.FORBIDDEN);
            }
            log.info("Verified JWT token of {}", token);
            String email = jwtUtil.extractUsername(token);
            exchange.getRequest().mutate()
                    .header("email", email).build();
        }
        return chain.filter(exchange);
    }

    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }
}
