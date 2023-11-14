package com.appscootercopy.scooterusemicroservice.service.dto.tariff;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class TariffRequestDTO {

    @NotNull(message = "cost cannot be null")
    @Min(value = 200, message ="ID should not be less than 200")
    private Double cost;
    @NotNull(message = "type cannot be null")
    private Long type;

}
