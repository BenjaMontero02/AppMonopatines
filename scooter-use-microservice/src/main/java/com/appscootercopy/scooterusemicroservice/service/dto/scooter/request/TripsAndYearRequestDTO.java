package com.appscootercopy.scooterusemicroservice.service.dto.scooter.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class TripsAndYearRequestDTO {

    @NotNull(message = "countTrips cannot be null")
    private Long minCountTrips;
    @NotNull(message = "the year cannot be NUll")
    private Long year;

}
