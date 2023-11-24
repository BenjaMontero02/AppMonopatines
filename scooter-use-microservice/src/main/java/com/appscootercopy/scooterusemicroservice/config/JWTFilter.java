package com.appscootercopy.scooterusemicroservice.config;

import com.appscootercopy.scooterusemicroservice.config.utils.JWTUtill;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtill jwtUtill;
    private final UserSecurityService userSecurityService;

    public JWTFilter(JWTUtill jwtUtill, UserSecurityService userSecurityService) {
        this.jwtUtill = jwtUtill;
        this.userSecurityService = userSecurityService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getRequestURI().startsWith("/scooter-microservice/swagger-ui/") || request.getRequestURI().startsWith("/scooter-microservice/api-docs")){
            filterChain.doFilter(request, response);
            return;
        }

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = header.split(" ")[1].trim();
        //CARGAR EL USUARIO AL USERDETAILSERVICE
        String email = jwtUtill.getUserName(token);

        User user = (User) userSecurityService.loadUserByUsername(email);

        //CARGAR USUARIO EN EL CONTEXTO DE SEGURIDAD
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword(), userSecurityService.grantedAuthorities(jwtUtill.getClaims(token)));

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
