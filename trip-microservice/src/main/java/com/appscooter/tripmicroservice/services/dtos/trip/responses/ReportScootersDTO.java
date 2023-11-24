package com.appscooter.tripmicroservice.services.dtos.trip.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReportScootersDTO {

    private String licenseScooter;
    private Long countTrips;
    private Double kms;

}
