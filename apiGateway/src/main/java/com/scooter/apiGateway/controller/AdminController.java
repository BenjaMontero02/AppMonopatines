package com.scooter.apiGateway.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.scooter.apiGateway.service.Constants;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping("/hello")
    @PreAuthorize("hasRole('" + Constants.ADMIN + "')")
    public ResponseEntity hello(){
        return new ResponseEntity("Hello Admin", HttpStatus.OK);
    }
}
