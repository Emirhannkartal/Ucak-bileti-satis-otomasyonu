package com.samilemir.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.samilemir.handler.AuthEntryPoint;
import com.samilemir.jwt.JWTAuthenticationFilter;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public static final String REGISTER = "/register";
    public static final String AUTHENTICATE = "/authenticate";
    public static final String REFRESH_TOKEN = "/refreshToken";

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private AuthEntryPoint authEntryPoint;

    @Autowired
    private JWTAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                  .cors(cors -> cors.configurationSource(corsConfigurationSource())) // CORS yapılandırması
                  .csrf(csrf -> csrf.disable())
                  .authorizeRequests(auth -> auth
                      .requestMatchers(REGISTER, AUTHENTICATE, REFRESH_TOKEN).permitAll()
                      .requestMatchers("/api/v1/airlines/**").authenticated()
                      .requestMatchers("/api/v1/airport/**").authenticated()
                      .requestMatchers("/passengers/**").authenticated()
                      .requestMatchers("/api/v1/checkin/**").authenticated()
                      .anyRequest().authenticated()
                  )
                  .exceptionHandling(exception -> exception
                      .authenticationEntryPoint(authEntryPoint)
                  )
                  .sessionManagement(session -> session
                      .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                  )
                  .authenticationProvider(authenticationProvider)
                  .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                  .build();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOriginPattern("http://localhost:5173");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    private UrlBasedCorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOriginPattern("http://localhost:5173");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
