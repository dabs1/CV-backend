package com.cv.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Value("${app.cors.allowed-origins}")
    private String allowedOrigins;

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Allow sending credentials/tokens
        config.setAllowCredentials(true);

        // Read origins and add each to the allowed origins list
        for (String origin : allowedOrigins.split(",")) {
            config.addAllowedOrigin(origin.trim());
        }

        // Allow all headers and methods (GET, POST, PUT, OPTIONS, etc.)
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        // Apply this configuration to all API paths
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}