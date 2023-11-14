package com.scooter.apiGateway.controller;

import com.scooter.apiGateway.DTO.UserRequestDTO;
import com.scooter.apiGateway.DTO.UserResponseDTO;
import com.scooter.apiGateway.utils.JWTUtill;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JWTUtill jwtUtill;
    private WebClient webClient;

    private PasswordEncoder passwordEncoder;


    public AuthController(AuthenticationManager authenticationManager, JWTUtill jwtUtill) {
        this.authenticationManager = authenticationManager;
        this.jwtUtill = jwtUtill;
        this.webClient = WebClient.create("http://192.168.208.66:8081");
        this.passwordEncoder = new BCryptPasswordEncoder(16);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UserRequestDTO user){
        UserResponseDTO userDTO = webClient
                .get()
                .uri("/api/users/login/" + user.getMail())
                .retrieve()
                .bodyToMono(UserResponseDTO.class)
                .block();

        if(userDTO != null && passwordEncoder.matches(user.getPassword(), userDTO.getPassword())){
            String jwt = this.jwtUtill.createToken(user.getMail());
            return ResponseEntity.ok().header("Authorization", jwt).build();
        }
    return new ResponseEntity("invalid user", HttpStatus.NOT_FOUND);
    }
}
