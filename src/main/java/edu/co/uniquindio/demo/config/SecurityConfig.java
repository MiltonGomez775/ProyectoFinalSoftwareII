package edu.co.uniquindio.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desactiva CSRF (solo para pruebas)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/sesiones/**").permitAll() // permite login sin token
                .anyRequest().permitAll()
            );
        return http.build();
    }
}
