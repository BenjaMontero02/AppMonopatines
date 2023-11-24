package com.appscootercopy.scooterusemicroservice.service.dto.scooter.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportScootersDTO {

    private String licenseScooter;
    private Long countTrips;
    private Double kms;

}
