package com.ScootersApp.Service.DTOs.account.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@RequiredArgsConstructor
@Data
public class AccountRequestDTO {
    @NotNull(message = "ID cannot be null")
    @Min(value = 1, message ="ID should not be less than 1")
    private Long id;
    @Min(value = 0, message ="wallet should not be less than 0")
    @NotNull(message = "wallet cannot be null")
    private Double wallet;
}
