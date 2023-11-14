package com.scooter.apiGateway.service;

import com.scooter.apiGateway.DTO.UserRequestCreateDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UsersService {

    private WebClient webClient;
    private PasswordEncoder passwordEncoder;

    public UsersService() {
        this.webClient = WebClient.create("http://192.168.208.66:8081");
        this.passwordEncoder = new BCryptPasswordEncoder(16);
    }

    public Mono<ResponseEntity<Void>> disableUsers(String email){
        return webClient.post()
                .uri("/api/users/disable")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(email)
                .retrieve()
                .toBodilessEntity();
    }

    public Mono<ResponseEntity<Long>> createUsers(UserRequestCreateDTO user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user);
        return webClient.post()
                .uri("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(user)
                .retrieve()
                .toEntity(Long.class);
    }
}
