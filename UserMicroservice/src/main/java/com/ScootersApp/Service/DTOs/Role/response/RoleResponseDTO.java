package com.ScootersApp.Service.DTOs.Role.response;

import com.ScootersApp.domain.Role;
import lombok.Data;

@Data
public class RoleResponseDTO {
    private String type;

    public RoleResponseDTO(Role role) {
        this.type = role.getType();
    }
}
