package com.appscootercopy.scooterusemicroservice.service.dto.ubication.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UbicationRequestDTO {

    @NotNull(message = "ID cannot be null")
    @NotEmpty(message = "")
    private Double x;
    @NotNull(message = "")
    @NotEmpty(message = "")
    private Double y;
}
