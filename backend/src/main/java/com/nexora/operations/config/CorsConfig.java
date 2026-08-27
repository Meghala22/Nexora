package com.nexora.operations.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;
import java.util.List;

@Configuration
public class CorsConfig {
  @Bean CorsConfigurationSource corsConfigurationSource(@Value("${nexora.security.allowed-origins:http://localhost:4200,http://127.0.0.1:4200}") List<String> origins) {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(origins); configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    configuration.setAllowedHeaders(List.of("Authorization", "Content-Type", "X-Correlation-ID")); configuration.setExposedHeaders(List.of("X-Correlation-ID"));
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(); source.registerCorsConfiguration("/api/**", configuration); return source;
  }
}
