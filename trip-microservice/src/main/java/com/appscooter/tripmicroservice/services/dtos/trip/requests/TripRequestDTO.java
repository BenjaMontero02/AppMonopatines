package com.appscooter.tripmicroservice.services.dtos.trip.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@RequiredArgsConstructor
@Data
public class TripRequestDTO {
    @NotNull(message = "ID cannot be null")
    @Min(value = 0, message ="ID should not be less than 0")
    private Long id;
    @NotNull(message = "initTime cannot be null")
    private Timestamp initTime;
    @Min(value = 0, message ="kms should not be less than 0")
    @NotNull(message = "kms cannot be null")
    private Double kms;
    @NotNull(message = "licenseScooter cannot be null")
    @NotEmpty(message = "the licenseScooter cannot be empty")
    private String licenseScooter;
    @NotNull(message = "userEmail cannot be null")
    @NotEmpty(message = "the userEmail cannot be empty")
    private String userEmail;
}
