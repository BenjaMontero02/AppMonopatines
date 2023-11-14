package com.ScootersApp.Service.DTOs.Role.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class RoleRequest {

    @NotNull(message = "type cannot be null")
    @NotEmpty(message = "type cannot be empty")
    private String type;
}
