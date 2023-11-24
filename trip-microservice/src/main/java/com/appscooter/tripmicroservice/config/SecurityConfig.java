package com.appscooter.tripmicroservice.config;

import com.appscooter.tripmicroservice.services.Constants;
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
                            .requestMatchers( HttpMethod.GET, "api/trips/scooter/{licensePlate}").hasAnyRole(Constants.ADMIN, Constants.MANAGER)
                            .requestMatchers( HttpMethod.DELETE, "api/trips/{id}", "api/trips/license-scooter/{licenseScooter}").hasAnyRole(Constants.ADMIN, Constants.MANAGER)
                            .requestMatchers( HttpMethod.GET, "api/trips/profits/{year}", "api/trips/prices/", "api/trips/scooters/trips&year").hasRole(Constants.ADMIN)
                            .requestMatchers( HttpMethod.POST, "api/trips/prices").hasRole(Constants.ADMIN)
                            .requestMatchers( HttpMethod.GET,
                                    "api/trips/report/kms",
                                    "api/trips/report/pauses",
                                    "api/trips/report/non&pauses" ).hasRole(Constants.MANAGER)
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