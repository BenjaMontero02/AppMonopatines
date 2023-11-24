package com.appscooter.tripmicroservice.services.dtos.trip.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class TripsAndYearRequestDTO {

    @NotNull(message = "mincountTrips cannot be null")
    private Long minCountTrips;
    @NotNull(message = "the year cannot be NUll")
    private Long year;

}
