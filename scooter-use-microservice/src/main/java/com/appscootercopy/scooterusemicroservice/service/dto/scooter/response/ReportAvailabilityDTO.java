package com.appscootercopy.scooterusemicroservice.service.dto.scooter.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReportAvailabilityDTO {

    private Long countAvailable;
    private Long countNotAvailable;

}
