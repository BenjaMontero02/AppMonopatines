package com.scooter.apiGateway.config;

import com.scooter.apiGateway.service.UserSecurityService;
import com.scooter.apiGateway.utils.JWTUtill;
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
        //1. VALIDAR QUE EL HEADER SEA VALIDO
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(header == null || header.isEmpty() || !header.startsWith("Bearer")){
            filterChain.doFilter(request, response);
            return;
        }

        //2. VALIDAR QUE EL TOKEN SEA VALIDO
        String token = header.split(" ")[1].trim();

        if(!this.jwtUtill.isValid(token)){
            filterChain.doFilter(request, response);
            return;
        }

        //3. CARGAR EL USUARIO AL USERDETAILSERVICE
        String email = jwtUtill.getUserName(token);
        User user = (User) userSecurityService.loadUserByUsername(email);

        //4. CARGAR USUARIO EN EL CONTEXTO DE SEGURIDAD
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword(), user.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
