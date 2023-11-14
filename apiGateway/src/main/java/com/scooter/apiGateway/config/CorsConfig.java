package com.scooter.apiGateway.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Bean
    public static CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration cors = new CorsConfiguration();
        cors.setAllowedOrigins(Arrays.asList("http://localhost:8080")); // agrego de donde dejo entrar las peticiones, puedo agregar mas
        cors.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE")); //indico q metodos permito q entren
        cors.setAllowedHeaders(Arrays.asList("*"));// con * indico q todos los header son permitidos
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",cors); // {//**} esto sirve p indicarle que todos los controladores
        //tienen q respetar la configuracion del cors
        return source;
    }
}
