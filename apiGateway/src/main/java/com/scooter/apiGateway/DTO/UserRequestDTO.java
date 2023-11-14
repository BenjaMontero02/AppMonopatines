package com.scooter.apiGateway.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.*;
@Data
@AllArgsConstructor
public class UserRequestDTO {
    @NotNull
    private String mail;
    @NotNull
    private String password;
}
