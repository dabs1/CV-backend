package com.cv.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${app.security.api-key}")
    private String expectedApiKey;

    private static final String API_KEY_HEADER = "X-API-KEY";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF for stateless APIs
                .cors(org.springframework.security.config.Customizer.withDefaults()) // Enable CORS integration
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/cv/**").permitAll() // Public access to view CV
                        .requestMatchers("/api/admin/**").authenticated() // Protect admin routes
                        .anyRequest().permitAll()
                )
                // Add our custom API key filter before the standard authentication filter
                .addFilterBefore(new ApiKeyAuthFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // Custom Filter to check the X-API-KEY header
    private class ApiKeyAuthFilter extends OncePerRequestFilter {
        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                throws ServletException, IOException {

            String path = request.getRequestURI();

            // Only apply the filter to admin paths
            if (!path.startsWith("/api/admin")) {
                filterChain.doFilter(request, response);
                return;
            }

            String providedApiKey = request.getHeader(API_KEY_HEADER);

            // Check if the provided key matches the one in application.yml
            if (expectedApiKey != null && expectedApiKey.equals(providedApiKey)) {
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken("admin", null, AuthorityUtils.NO_AUTHORITIES);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Unauthorized: Missing or Invalid X-API-KEY");
                return;
            }

            filterChain.doFilter(request, response);
        }
    }
}