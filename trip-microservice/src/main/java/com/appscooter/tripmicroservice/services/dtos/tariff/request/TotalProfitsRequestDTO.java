package com.appscooter.tripmicroservice.services.dtos.tariff.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class TotalProfitsRequestDTO {

    @NotNull(message = "firstMonth cannot be null")
    @Min(value = 1, message ="firstMonth should not be less than 1")
    @Max(value = 12, message ="firstMonth should not be greater than 12")
    private Long firstMonth;
    @NotNull(message = "lastMonth cannot be null")
    @Min(value = 1, message ="lastMonth should not be less than 1")
    @Max(value = 12, message ="lastMonth should not be greater than 12")
    private Long lastMonth;
    @NotNull(message = "year cannot be null")
    @Min(value = 2022, message ="year should not be less than 2022")
    @Max(value = 2024, message ="year should not be greater than 2024")
    private Long year;

}
