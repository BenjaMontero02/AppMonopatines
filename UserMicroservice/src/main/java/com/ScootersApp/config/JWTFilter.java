package com.ScootersApp.config;

import com.ScootersApp.config.utils.JWTUtill;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.http.protocol.HTTP;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

    private final String hashFakePassword = "$2a$16$p6lo2eRCFAKGrUCVXD9gceSdqtBx7.2CvQ4X3BhQWUrAhYV7lyvRC";


    public JWTFilter(JWTUtill jwtUtill, UserSecurityService userSecurityService) {
        this.jwtUtill = jwtUtill;
        this.userSecurityService = userSecurityService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if((request.getRequestURI().startsWith("/user-microservice/api/users/") && request.getMethod().equals("POST"))){
            filterChain.doFilter(request, response);
            return;
        }

        if(request.getRequestURI().startsWith("/user-microservice/swagger-ui/") || request.getRequestURI().startsWith("/user-microservice/api-docs")){
            filterChain.doFilter(request, response);
            return;
        }

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        String token = header.split(" ")[1].trim();

        String email = jwtUtill.getUserName(token);

        // CARGAR EL USUARIO AL USERDETAILSERVICE
        User user = (User) userSecurityService.loadUserByUsername(email);


        // CARGAR USUARIO EN EL CONTEXTO DE SEGURIDAD
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword(), userSecurityService.grantedAuthorities(jwtUtill.getClaims(token)));

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
