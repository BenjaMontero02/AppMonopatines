package com.scooter.apiGateway.controller;

import com.scooter.apiGateway.DTO.*;
import com.scooter.apiGateway.domain.Role;
import com.scooter.apiGateway.domain.User;
import com.scooter.apiGateway.repository.UserRepository;
import com.scooter.apiGateway.utils.JWTUtill;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.ArrayList;
import java.util.List;import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final JWTUtill jwtUtill;

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public AuthController(JWTUtill jwtUtill, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.jwtUtill = jwtUtill;
        this.passwordEncoder = new BCryptPasswordEncoder(16);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UserRequestDTO user){
        User u = this.userRepository.findByMail(user.getMail());
        if(u!= null && passwordEncoder.matches(user.getPassword(), u.getPassword())){
            List<String> rolesUser = new ArrayList<>();
            for (Role role : u.getRoles()){
                rolesUser.add(role.getType());
            }
            String token = this.jwtUtill.createToken(u.getMail(), rolesUser);
            return new ResponseEntity(token, HttpStatus.OK);
        }
        return new ResponseEntity("invalid user", HttpStatus.NOT_FOUND);
    }

}
