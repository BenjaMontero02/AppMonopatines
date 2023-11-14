package com.appscootercopy.scooterusemicroservice.service.dto.trip.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@RequiredArgsConstructor
@Data
public class TripRequestDTO {
    @NotNull(message = "ID cannot be null")
    @Min(value = 0, message ="ID should not be less than 0")
    private Long id;
    @NotNull(message = "initTime cannot be null")
    private Timestamp initTime;
    @NotNull(message = "endTime cannot be null")
    private Timestamp endTime;
    @Min(value = 0, message ="kms should not be less than 0")
    @NotNull(message = "kms cannot be null")
    private Double kms;
    @NotNull(message = "endend cannot be null")
    private Boolean ended;
}
