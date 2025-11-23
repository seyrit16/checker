package org.example.checker.configuration.security;

import org.example.checker.configuration.security.components.CustomAuthenticationEntryPoint;
import org.example.checker.configuration.security.components.JwtAuthenticationFilter;
import org.example.checker.repository.UserRepository;
import org.example.checker.service.web.CheckerUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final UserRepository userRepository;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public WebSecurityConfig(UserRepository userRepository,
                             CustomAuthenticationEntryPoint customAuthenticationEntryPoint,
                             JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.userRepository = userRepository;
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(customAuthenticationEntryPoint))
                .csrf(AbstractHttpConfigurer::disable)
                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )
                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public CheckerUserDetailsService userDetailsService() {
        return new CheckerUserDetailsService(this.userRepository);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(6);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:5173", "http://localhost:8000"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) {
        return config.getAuthenticationManager();
    }
}
