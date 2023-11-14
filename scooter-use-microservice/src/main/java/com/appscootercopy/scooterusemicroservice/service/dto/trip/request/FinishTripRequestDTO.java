package com.appscootercopy.scooterusemicroservice.service.dto.trip.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Data
@RequiredArgsConstructor
public class FinishTripRequestDTO {

    @Min(value = 0, message ="kms should not be less than 0")
    @NotNull(message = "kms cannot be null")
    private Double kms;
    @NotNull(message = "endend cannot be null")
    private Boolean ended;
}
