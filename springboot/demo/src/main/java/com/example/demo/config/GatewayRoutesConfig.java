package com.example.demo.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRoutesConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder, GatewayServicesProperties properties) {
        return builder.routes()
                .route("auth-service", r -> r
                        .path("/auth/**")
                        .uri(properties.getAuthBaseUrl().toString()))
                .route("loja-service", r -> r
                        .path("/api/**")
                        .uri(properties.getLojaBaseUrl().toString()))
                .build();
    }
}
