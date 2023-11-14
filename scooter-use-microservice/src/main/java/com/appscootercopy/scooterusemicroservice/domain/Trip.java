package com.appscootercopy.scooterusemicroservice.domain;

import com.appscootercopy.scooterusemicroservice.service.timer.TimerPause;
import com.appscootercopy.scooterusemicroservice.service.dto.trip.request.TripRequestDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    private PauseTrip pause;
    @Transient
    private TimerPause timer;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Tariff tariff;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Tariff tariffExtra;
    @Column(nullable = false)
    private String licenseScooterAssociated;


    public Trip(TripRequestDTO requestDTO) {
        this.id = requestDTO.getId();
        this.initTime = requestDTO.getInitTime();
        this.endTime = requestDTO.getEndTime();
        this.kms = requestDTO.getKms();
        this.ended = requestDTO.getEnded();
        this.pause = null;
        this.timer = null;
        this.tariffExtra = null;
        this.licenseScooterAssociated = requestDTO.getLicenseScooter();
    }

    public Trip(TripRequestDTO requestDTO, Double priceService) {
        this.id = requestDTO.getId();
        this.initTime = requestDTO.getInitTime();
        this.endTime = requestDTO.getEndTime();
        this.kms = requestDTO.getKms();
        this.ended = requestDTO.getEnded();
        this.tariff = new Tariff(priceService, 1L);
        this.pause = null;
        this.timer = null;
        this.tariffExtra = null;
        this.licenseScooterAssociated = requestDTO.getLicenseScooter();
    }

    public Trip(Long id, Timestamp initTime, Timestamp endTime,
                Double kms, Boolean ended, Double priceService, String scooter) {
        this.id = id;
        this.initTime = initTime;
        this.endTime = endTime;
        this.kms = kms;
        this.ended = ended;
        this.tariff = new Tariff(priceService,1L);
        this.pause = null;
        this.timer = null;
        this.tariffExtra = null;
        this.licenseScooterAssociated = scooter;
    }

}
