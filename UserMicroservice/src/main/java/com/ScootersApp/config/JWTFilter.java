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

    public JWTFilter(JWTUtill jwtUtill, UserSecurityService userSecurityService) {
        this.jwtUtill = jwtUtill;
        this.userSecurityService = userSecurityService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //falsifico un usuario para que admita el post a crear usuario
        if(request.getRequestURI().equals("/api/users/") && request.getMethod().equals("POST")){
            List<String> fakeRole = new ArrayList<String>();
            fakeRole.add("user");
            this.userSecurityService.setRoles(fakeRole);
            User user = (User) userSecurityService.loadUserByUsername("fakeEmail");
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(
                    user.getUsername(), user.getPassword(), user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request, response);
            return;
        }
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);


        String token = header.split(" ")[1].trim();


        //3. CARGAR EL USUARIO AL USERDETAILSERVICE
        String email = jwtUtill.getUserName(token);

        this.userSecurityService.setRoles(jwtUtill.getClaims(token));
        User user = (User) userSecurityService.loadUserByUsername(email);


        //4. CARGAR USUARIO EN EL CONTEXTO DE SEGURIDAD
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword(), user.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
