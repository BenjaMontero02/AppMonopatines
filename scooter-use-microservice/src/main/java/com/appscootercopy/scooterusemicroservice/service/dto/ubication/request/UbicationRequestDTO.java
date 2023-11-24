package com.appscootercopy.scooterusemicroservice.service.dto.ubication.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UbicationRequestDTO {

    @NotNull(message = "The x cannot be null")
    private Double x;
    @NotNull(message = "The y cannot be null")
    private Double y;

    public UbicationRequestDTO(Double x, Double y) {
        this.x = x;
        this.y = y;
    }
}
