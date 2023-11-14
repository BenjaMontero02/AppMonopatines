package com.scooter.apiGateway.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestCreateDTO {

    private String name;
    private String surname;
    private String mail;
    private String password;
    private String phoneNumber;
    private List<String> roles;

}
