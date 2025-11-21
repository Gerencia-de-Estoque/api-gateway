package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.test.context.ActiveProfiles;

import reactor.core.publisher.Flux;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class GatewayRoutesIntegrationTest {

    @Autowired
    private RouteLocator routeLocator;

    @Test
    void deveRegistrarRotasDeAuthELoja() {
        List<String> routeIds = Flux.from(routeLocator.getRoutes())
                .map(route -> route.getId())
                .collectList()
                .block();

        assertThat(routeIds).isNotNull();
        assertThat(routeIds).contains("auth-service", "loja-service");
    }
}
