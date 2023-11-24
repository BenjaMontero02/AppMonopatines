package com.apigateway.config;

import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouterValidator {

    public static final List<String> openApiEndpoints = List.of(
            "/auth-microservice/api/auth/login",
            "/user-microservice/api/users/"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> {
                        if (request.getURI().getPath().startsWith("/user-microservice/api/users/") && request.getMethod().equals(HttpMethod.POST)) return true;
                        if(request.getURI().getPath().contains("swagger-ui"))  return true;
                        if(request.getURI().getPath().contains("api-docs"))  return true;
                        if(request.getURI().getPath().startsWith(uri))  return true;
                        return false;
                    });

}

