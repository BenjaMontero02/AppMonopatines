package com.appscootercopy.scooterusemicroservice.service.dto.scooterTrip;

import com.appscootercopy.scooterusemicroservice.domain.ScooterStop;
import com.appscootercopy.scooterusemicroservice.domain.Ubication;
import lombok.Data;

@Data
public class ScooterStopResponseDTO {

    private Long id;
    private Ubication ubication;

    public ScooterStopResponseDTO(ScooterStop scooterStop) {
        this.id = scooterStop.getId();
        this.ubication = scooterStop.getUbication();
    }
}
