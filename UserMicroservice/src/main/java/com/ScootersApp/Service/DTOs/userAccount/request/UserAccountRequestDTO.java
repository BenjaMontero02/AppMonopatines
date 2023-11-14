package com.ScootersApp.Service.DTOs.userAccount.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserAccountRequestDTO {
    @NotNull(message = "The id from account cannot be null")
    private final Long accountId;
    @NotNull(message = "The id from user cannot be null")
    private final Long userID;
}
