package com.appscootercopy.scooterusemicroservice.service.dto.trip;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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
