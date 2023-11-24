package com.appscootercopy.scooterusemicroservice.config;

import com.appscootercopy.scooterusemicroservice.service.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {

    private final JWTFilter jwtFilter;

    public SecurityConfig(JWTFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)// descativo el crsf
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> {
                    authorize
                            .requestMatchers("/v1/authenticate","/v3/api-docs/**", "/api-docs/**", "/api-docs", "/swagger-ui.html", "/swagger-ui/**").permitAll()
                            .requestMatchers( HttpMethod.GET,
                                    "api/scooters/report/kms",
                                    "api/scooters/report/pauses",
                                    "api/scooters/report/non&pauses" ).hasRole(Constants.MANAGER)
                            .requestMatchers( HttpMethod.GET, "api/scooters/trips&year","api/scooters/availability").hasRole(Constants.ADMIN)
                            .requestMatchers( HttpMethod.POST, "api/scooters").hasRole(Constants.ADMIN)
                            .requestMatchers( HttpMethod.DELETE, "api/scooters/{id}").hasRole(Constants.ADMIN)
                            .requestMatchers( HttpMethod.PUT, "api/scooters/{id}").hasRole(Constants.ADMIN)
                            .requestMatchers( HttpMethod.PATCH, "api/scooters/{id}").hasAnyRole(Constants.ADMIN, Constants.MANAGER)
                            .requestMatchers( HttpMethod.POST, "api/scooters/stops").hasAnyRole(Constants.ADMIN, Constants.MANAGER)
                            .requestMatchers( HttpMethod.DELETE, "api/scooters/stops/{id}").hasAnyRole(Constants.ADMIN, Constants.MANAGER)
                            .requestMatchers( HttpMethod.PUT, "api/scooters/stops/{id}").hasAnyRole(Constants.ADMIN, Constants.MANAGER)
                            .anyRequest()
                            .authenticated();
                } )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}