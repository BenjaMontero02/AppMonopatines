package com.appscootercopy.scooterusemicroservice.service.dto.scooterStop.request;

import com.appscootercopy.scooterusemicroservice.domain.Ubication;
import com.appscootercopy.scooterusemicroservice.service.dto.ubication.request.UbicationRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ScooterStopRequestDTO {

    @NotNull(message = "The ubication from scooterStop cannot be null")
    @Valid
    private Ubication ubication;
}
