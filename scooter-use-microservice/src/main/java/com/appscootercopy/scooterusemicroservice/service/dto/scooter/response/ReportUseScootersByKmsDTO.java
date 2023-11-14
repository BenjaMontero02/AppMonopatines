package com.appscootercopy.scooterusemicroservice.service.dto.scooter.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReportUseScootersByKmsDTO {

    private Long id;
    private String licensePlate;
    private Boolean available;
    private Long countTrips;
    private Double kms;

}
