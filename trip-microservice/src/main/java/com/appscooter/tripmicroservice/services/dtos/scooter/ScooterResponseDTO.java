package com.appscooter.tripmicroservice.services.dtos.scooter;

import lombok.Data;

import java.io.Serializable;

@Data
public class ScooterResponseDTO implements Serializable {

    private Long id;
    private String licensePLate;
    private Boolean available;
    private Long ubication_id;

}
