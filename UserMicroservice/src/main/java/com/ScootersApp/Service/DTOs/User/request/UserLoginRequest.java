package com.ScootersApp.Service.DTOs.User.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Data
@Getter
@AllArgsConstructor
public class UserLoginRequest {
    @NotNull(message = "mail cannot be null")
    @NotEmpty(message = "mail cannot be empty")
    private String mail;
    @NotNull(message = "password cannot be null")
    @NotEmpty(message = "password cannot be empty")
    private String password;
}
