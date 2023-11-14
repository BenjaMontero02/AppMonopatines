package com.appscootercopy.scooterusemicroservice.service.dto.trip.response;

import com.appscootercopy.scooterusemicroservice.domain.Trip;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class TripResponseDTO {

    private Long id;
    private Timestamp initTime;
    private Timestamp endTime;
    private Double kms;
    private Boolean ended;

    public TripResponseDTO(Trip trip) {
        this.id=trip.getId();
        this.initTime=trip.getInitTime();
        this.endTime=trip.getEndTime();
        this.kms= trip.getKms();
        this.ended=trip.getEnded();
    }


}
