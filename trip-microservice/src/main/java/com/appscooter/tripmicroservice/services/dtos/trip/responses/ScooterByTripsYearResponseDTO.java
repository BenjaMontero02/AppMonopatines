package com.appscooter.tripmicroservice.services.dtos.trip.responses;

import lombok.Data;

@Data
public class ScooterByTripsYearResponseDTO {

    private String licenseScooter;
    private Long countTrips;
    private Long year;

    public ScooterByTripsYearResponseDTO(String licenseScooter, Long countTrips, Long year) {
        this.licenseScooter = licenseScooter;
        this.countTrips = countTrips;
        this.year = year;
    }
}
