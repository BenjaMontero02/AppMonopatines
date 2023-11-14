package com.appscootercopy.scooterusemicroservice.service.dto.scooterStop.request;

import com.appscootercopy.scooterusemicroservice.domain.Ubication;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ScooterStopRequestDTO {

    @NotNull(message = "The ubication from scooterStop cannot be null")
    private final Ubication ubication;
}
