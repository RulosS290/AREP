package com.escuelaing.jpa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder; 
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuration class for Spring Security settings.
 * Configures CORS, CSRF, authorization rules, and password encoding.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configures the security filter chain for the application.
     * Allows public access to authentication and property-related endpoints,
     * while securing all other requests.
     *
     * @param http The HttpSecurity object to configure security.
     * @return The configured SecurityFilterChain.
     * @throws Exception In case of configuration errors.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors()
            .and()
            .csrf().disable()
            .authorizeHttpRequests((authz) -> authz
                .requestMatchers("/auth/**", "/properties/**").permitAll()
                .anyRequest().authenticated()
            );
        
        return http.build();
    }

    /**
     * Bean for password encoding using BCrypt.
     * 
     * @return A PasswordEncoder instance for encrypting passwords.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
