package com.scooter.apiGateway.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JWTUtill {
    private static final String SECRET_KEY = "APP_MONOPATIN";
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);
    private static final Long TIMER_OF_EXPIRATION = TimeUnit.MINUTES.toMillis(60);

    public String createToken(String username){
        return JWT.create()
                .withSubject(username)
                .withIssuer("App_Monopatin")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TIMER_OF_EXPIRATION))
                .sign(ALGORITHM);
    }

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
