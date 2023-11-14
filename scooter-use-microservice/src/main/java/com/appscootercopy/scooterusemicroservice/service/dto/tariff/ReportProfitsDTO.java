package com.appscootercopy.scooterusemicroservice.service.dto.tariff;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReportProfitsDTO {

    private String month;
    private String year;
    private Double totalProfits;
}
