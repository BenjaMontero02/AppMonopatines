package com.appscootercopy.scooterusemicroservice.service.dto.scooterTrip.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ScooterTripRequestDTO {
    @NotNull(message = "The tripId from ScooterTrip cannot be null")
    private final Long scooterId;
    @NotNull(message = "The tripId from ScooterTrip cannot be null")
    private final Long tripId;
}
