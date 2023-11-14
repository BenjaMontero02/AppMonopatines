package com.appscootercopy.scooterusemicroservice.service.dto.scooter.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class EnableScooterRequestDTO {
    @NotNull(message = "Available cannot be null")
    private Boolean available;
}
