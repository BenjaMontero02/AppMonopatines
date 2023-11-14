package com.apigateway.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Component;

@Component
public class JwtUtill {

    private static final String SECRET_KEY = "APP_MONOPATIN";
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);
    public boolean isValid(String token) {
        try {
            JWT.require(ALGORITHM)
                    .build()
                    .verify(token);
            return true;
        }catch (JWTVerificationException e){
            return false;
        }
    }

    public String getUserName(String token) {
        return JWT.require(ALGORITHM)
                .build()
                .verify(token)
                .getSubject();
    }
}
