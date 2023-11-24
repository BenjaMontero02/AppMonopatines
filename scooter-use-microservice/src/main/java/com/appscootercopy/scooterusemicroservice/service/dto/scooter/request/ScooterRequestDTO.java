package com.appscootercopy.scooterusemicroservice.service.dto.scooter.request;

import com.appscootercopy.scooterusemicroservice.domain.Ubication;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.val;

@RequiredArgsConstructor
@Data
public class ScooterRequestDTO {
    @NotNull(message = "Available cannot be null")
    private Boolean available;
    @NotNull(message = "the licensePlate cannot be NUll")
    @NotEmpty(message = "licensePlate cannot be empty")
    private String licensePlate;
    @NotNull(message = "the ubication cannot be NUll")
    private Ubication ubication;

}
