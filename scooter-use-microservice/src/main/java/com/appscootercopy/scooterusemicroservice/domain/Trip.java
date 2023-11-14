package com.appscootercopy.scooterusemicroservice.domain;

import com.appscootercopy.scooterusemicroservice.service.dto.trip.request.TripRequestDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
public class Trip {

    @Id
    private Long id;
    @Column(nullable = false)
    private Timestamp initTime;
    @Column(nullable = true)
    private Timestamp endTime;
    @Column(nullable = false)
    private Double kms;
    @Column(nullable = false)
    private Boolean ended;
    //@Column
    //private Timer pause;

    public Trip(TripRequestDTO requestDTO) {
        this.id = requestDTO.getId();
        this.initTime = requestDTO.getInitTime();
        this.endTime = requestDTO.getEndTime();
        this.kms = requestDTO.getKms();
        this.ended = requestDTO.getEnded();
    }

    public Trip(Long id, Timestamp initTime, Timestamp endTime, Double kms, Boolean ended) {
        this.id = id;
        this.initTime = initTime;
        this.endTime = endTime;
        this.kms = kms;
        this.ended = ended;
    }
}
