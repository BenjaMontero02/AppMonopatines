package com.appscootercopy.scooterusemicroservice.service.dto.scooter.response;

import com.appscootercopy.scooterusemicroservice.domain.Scooter;
import com.appscootercopy.scooterusemicroservice.domain.Ubication;
import lombok.Data;

import java.io.Serializable;

@Data
public class ScooterResponseDTO implements Serializable {

    private Long id;
    private String licensePLate;
    private Boolean available;
    private Long ubication_id;

    public ScooterResponseDTO(Scooter scooter) {
        this.id = scooter.getId();
        this.licensePLate = scooter.getLicensePLate();
        this.ubication_id = scooter.getUbication().getId();
        this.available = scooter.getAvailable();
    }

}
