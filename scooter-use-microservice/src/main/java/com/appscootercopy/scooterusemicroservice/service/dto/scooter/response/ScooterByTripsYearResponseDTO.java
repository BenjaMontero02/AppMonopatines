package com.appscootercopy.scooterusemicroservice.service.dto.scooter.response;

import lombok.Data;

@Data
public class ScooterByTripsYearResponseDTO {

    private String licensePLate;
    private Boolean available;
    private Long countTrips;
    private Long year;

    public ScooterByTripsYearResponseDTO(String licensePLate, Boolean available, Long countTrips, Long year) {
        this.licensePLate = licensePLate;
        this.available = available;
        this.countTrips = countTrips;
        this.year = year;
    }
}
