//package com.cv.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class CorsConfig implements WebMvcConfigurer {
//
//    @Value("${app.cors.allowed-origins,https://cv-frontend-beryl.vercel.app}")
//    private String allowedOrigins;
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/api/**")
//                .allowedOrigins(allowedOrigins.split(",")) // Splits by comma just in case you add multiple URLs later
//                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
//                .allowedHeaders("*")
//                .allowCredentials(true);
//    }
//}
package com.cv.config; // Confirme se o package é este mesmo!

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

        // Permite enviar credenciais/tokens
        config.setAllowCredentials(true);

        // Lê a sua variável do Railway e adiciona cada link à lista VIP
        for (String origin : allowedOrigins.split(",")) {
            config.addAllowedOrigin(origin.trim());
        }

        // Permite todos os cabeçalhos e métodos (GET, POST, PUT, OPTIONS, etc.)
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        // Aplica esta regra a todos os caminhos da API
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}