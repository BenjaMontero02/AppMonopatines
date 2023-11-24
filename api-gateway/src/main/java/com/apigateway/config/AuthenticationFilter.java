package com.apigateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@RefreshScope
@Component
public class AuthenticationFilter implements GatewayFilter {

    private final RouterValidator routerValidator;
    private final JwtUtill jwtUtill;

    @Autowired
    public AuthenticationFilter(RouterValidator routerValidator, JwtUtill jwtUtill) {
        this.routerValidator = routerValidator;
        this.jwtUtill = jwtUtill;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        if (routerValidator.isSecured.test(request)) {
            if (this.isAuthMissing(request)) {
                return this.onError(exchange, HttpStatus.FORBIDDEN);
            }

            final String tokenCadena = this.getAuthHeader(request);

            /*
            * Hago esto ya que tokenCadena me deuelve "bearer token"
            * y solo necesito el token
            * */
            String[] cadena = tokenCadena.split(" ");

            final String token = cadena[1];

            if (!jwtUtill.isValid(token)) {
                return this.onError(exchange, HttpStatus.UNAUTHORIZED);
            }

            System.out.println("llego desp del trip");
            //logica para los roles
            this.updateRequest(exchange, token);
        }
        return chain.filter(exchange);
    }

    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    private String getAuthHeader(ServerHttpRequest request) {
        return request.getHeaders().getOrEmpty("Authorization").get(0);
    }

    private boolean isAuthMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }

    private void updateRequest(ServerWebExchange exchange, String token) {
        String mail = this.jwtUtill.getUserName(token);
        exchange.getRequest().mutate()
                .header("email", mail)
                .build();
    }
}
