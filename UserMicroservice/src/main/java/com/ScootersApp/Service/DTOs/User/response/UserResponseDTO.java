package com.ScootersApp.Service.DTOs.User.response;


import com.ScootersApp.domain.Role;
import com.ScootersApp.domain.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class UserResponseDTO {
    private final Long ID;
    private final String name;
    private final String surname;
    private final String mail;
    private final String phoneNumber;
    private final List<String> roles;

    public UserResponseDTO(User s1) {
        this.ID = s1.getID();
        this.name = s1.getName();
        this.surname = s1.getSurname();
        this.mail = s1.getMail();
        this.phoneNumber = s1.getPhoneNumber();
        this.roles = new ArrayList<>();
        ArrayList<String> rol = new ArrayList<>();
        for (Role r: s1.getRoles()) {
                rol.add(r.getType());
        }
        this.roles.addAll(rol);
    }
}
