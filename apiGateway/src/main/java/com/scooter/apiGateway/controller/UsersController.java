package com.scooter.apiGateway.controller;

import com.scooter.apiGateway.DTO.UserDTO;
import com.scooter.apiGateway.DTO.UserRequestCreateDTO;
import com.scooter.apiGateway.DTO.UserRequestDTO;
import com.scooter.apiGateway.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private UsersService usersService;

    public UsersController(UsersService usersService){
        this.usersService = usersService;
    }

    @PostMapping("/{email}/disable")
    public Mono<ResponseEntity> disableUser(@PathVariable String email, @RequestBody boolean status){
        System.out.println(status);
        return this.usersService.disableUsers(email)
                .map(voidResponseEntity -> {
                    if(voidResponseEntity.getStatusCode().is2xxSuccessful()){
                        return new ResponseEntity("The user with email: " + email, voidResponseEntity.getStatusCode());
                    }

                    return new ResponseEntity("Error", voidResponseEntity.getStatusCode());
                });
    }

    @PostMapping("/")
    public Mono<ResponseEntity<?>> createUser(@RequestBody UserRequestCreateDTO user){
        return this.usersService.createUsers(user)
                .map(voidResponseEntity -> {
                    if(voidResponseEntity.getStatusCode().is2xxSuccessful()){
                        return new ResponseEntity("User created ", voidResponseEntity.getStatusCode());
                    }

                    return new ResponseEntity("Error", voidResponseEntity.getStatusCode());
                });
    }
}
