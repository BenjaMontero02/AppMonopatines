package com.scooter.apiGateway.controller;

import com.scooter.apiGateway.DTO.*;

import com.scooter.apiGateway.grpcClient.GrpcUserClient;
import com.scooter.apiGateway.utils.JWTUtill;

import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final JWTUtill jwtUtill;
    private PasswordEncoder passwordEncoder;
    private GrpcUserClient grpcUserClient;

    public AuthController(JWTUtill jwtUtill, GrpcUserClient grpcUserClient) {
        this.grpcUserClient =grpcUserClient;
        this.jwtUtill = jwtUtill;
        this.passwordEncoder = new BCryptPasswordEncoder(16);
    }



    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UserRequestDTO user){
        UserResponseLoginDTO u = this.grpcUserClient.getUserByEmail(user.getMail());
        if(u!= null && passwordEncoder.matches(user.getPassword(), u.getPassword())){
            String token = this.jwtUtill.createToken(u.getMail(), u.getRoles());
            return new ResponseEntity(token, HttpStatus.OK);
        }
        return new ResponseEntity("invalid user", HttpStatus.NOT_FOUND);
    }

}
