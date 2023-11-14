package com.appscootercopy.scooterusemicroservice.service.dto.scooterTrip.response;

import com.appscootercopy.scooterusemicroservice.domain.Scooter;
import com.appscootercopy.scooterusemicroservice.domain.ScooterTripId;
import com.appscootercopy.scooterusemicroservice.domain.Trip;
import lombok.Data;

@Data
public class ScooterTripIdResponseDTO {

    private Long scooterId;
    private Long tripId;

    public ScooterTripIdResponseDTO(ScooterTripId scooterTripId) {
        this.scooterId = scooterTripId.getIdScooter().getId();
        this.tripId = scooterTripId.getIdTrip().getId();
    }
}
