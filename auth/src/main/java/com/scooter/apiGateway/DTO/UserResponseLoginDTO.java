package com.scooter.apiGateway.DTO;

import com.userServiceGRPC.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseLoginDTO {
    private String mail;
    private String password;
    private List<String> roles;

    public void setRoles(List<Role> rolesList) {
        this.roles = new ArrayList<>();
        for (Role role : rolesList){
            this.roles.add(role.getName());
        }
    }
}
