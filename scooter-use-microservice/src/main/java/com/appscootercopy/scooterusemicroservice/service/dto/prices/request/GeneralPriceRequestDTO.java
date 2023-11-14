package com.appscootercopy.scooterusemicroservice.service.dto.prices.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@RequiredArgsConstructor
@Data
public class GeneralPriceRequestDTO {

    @NotNull(message = "the priceService cannot be NUll")
    private Double priceService;
    @NotNull(message = "the priceInterest cannot be NUll")
    private Double priceInterest;
    @NotNull(message = "the current cannot be NUll")
    private Boolean current;
    @NotNull(message = "the dateValidity cannot be NUll")
    private Timestamp dateValidity;

}
