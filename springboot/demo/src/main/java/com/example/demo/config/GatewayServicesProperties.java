package com.example.demo.config;

import java.net.URI;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "app.services")
public class GatewayServicesProperties {

    @NotNull
    private URI authBaseUrl;

    @NotNull
    private URI lojaBaseUrl;

    public URI getAuthBaseUrl() {
        return authBaseUrl;
    }

    public void setAuthBaseUrl(URI authBaseUrl) {
        this.authBaseUrl = authBaseUrl;
    }

    public URI getLojaBaseUrl() {
        return lojaBaseUrl;
    }

    public void setLojaBaseUrl(URI lojaBaseUrl) {
        this.lojaBaseUrl = lojaBaseUrl;
    }
}
