package com.scooter.apiGateway.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserDTO {

    private String email;
    private String password;
    private List<String> roles;
}
