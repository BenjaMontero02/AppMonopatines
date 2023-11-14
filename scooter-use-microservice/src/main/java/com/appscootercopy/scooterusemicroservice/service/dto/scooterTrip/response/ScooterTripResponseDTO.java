package com.appscootercopy.scooterusemicroservice.service.dto.scooterTrip.response;

import com.appscootercopy.scooterusemicroservice.domain.ScooterTrip;
import lombok.Data;

@Data
public class ScooterTripResponseDTO {

    private ScooterTripIdResponseDTO id;

    public ScooterTripResponseDTO(ScooterTrip scooterTrip) {
        this.id = new ScooterTripIdResponseDTO(scooterTrip.getId());
    }
}
