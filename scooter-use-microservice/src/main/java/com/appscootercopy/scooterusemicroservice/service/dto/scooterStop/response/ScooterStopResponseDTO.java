package com.appscootercopy.scooterusemicroservice.service.dto.scooterStop.response;

import com.appscootercopy.scooterusemicroservice.domain.ScooterStop;
import com.appscootercopy.scooterusemicroservice.domain.Ubication;
import lombok.Data;

import java.io.Serializable;

@Data
public class ScooterStopResponseDTO implements Serializable {

    private Long id;
    private Double x;
    private Double y;

    public ScooterStopResponseDTO(ScooterStop scooterStop) {
        this.id = scooterStop.getId();
        this.x = scooterStop.getUbication().getX();
        this.y = scooterStop.getUbication().getY();
    }
}
