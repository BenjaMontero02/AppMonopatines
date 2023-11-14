package com.ScootersApp.controller;

import com.ScootersApp.Service.DTOs.Role.request.RoleRequest;
import com.ScootersApp.Service.DTOs.Role.response.RoleResponseDTO;
import com.ScootersApp.Service.RoleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("RolesController")
@RequestMapping("api/roles")
public class RoleController {

    private RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }
    @PostMapping("/")
    public ResponseEntity createRole(@RequestBody @Valid RoleRequest role){
        return  this.service.save(role);
    }

    @GetMapping("/")
    public List<RoleResponseDTO> getAllRoles(){
        return  this.service.findAll();
    }
}
