package com.ScootersApp.Service;

import com.ScootersApp.Service.DTOs.Role.request.RoleRequest;
import com.ScootersApp.Service.DTOs.Role.response.RoleResponseDTO;
import com.ScootersApp.Service.exception.ConflictExistException;
import com.ScootersApp.domain.Role;
import com.ScootersApp.repository.RoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {

    RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ResponseEntity save(RoleRequest role){
        if(!this.repository.existsByType(role.getType())){
            Role newRole = new Role(role.getType());
            this.repository.save(newRole);
            return new ResponseEntity(newRole.getType(), HttpStatus.CREATED);
        }
        else {
            throw new ConflictExistException("Role", "tipo(String)", role.getType());
        }
    }

    public List<RoleResponseDTO> findAll() {
        List<Role> roles = this.repository.findAll();
        return roles.stream().map(r1-> new RoleResponseDTO(r1)).collect(Collectors.toList());
    }
}
