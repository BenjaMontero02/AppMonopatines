package com.ScootersApp.config.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class JWTUtill {
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

    public List<String> getClaims(String token){
        return JWT.require(ALGORITHM)
                .build()
                .verify(token)
                .getClaim("roles").asList(String.class);
    }
}
