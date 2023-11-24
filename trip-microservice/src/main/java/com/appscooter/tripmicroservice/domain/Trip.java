package com.appscooter.tripmicroservice.domain;

import com.appscooter.tripmicroservice.services.dtos.trip.requests.TripRequestDTO;
import com.appscooter.tripmicroservice.services.timer.TimerPause;
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
    @Column
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
    private String licenseScooter;
    @Column
    private String userEmail;

    public Trip(TripRequestDTO requestDTO, Double priceService) {
        this.id = requestDTO.getId();
        this.initTime = requestDTO.getInitTime();
        this.endTime = null;
        this.kms = requestDTO.getKms();
        this.ended = false;
        this.tariff = new Tariff(priceService, 1L);
        this.pause = null;
        this.timer = null;
        this.tariffExtra = null;
        this.licenseScooter = requestDTO.getLicenseScooter();
        this.userEmail = requestDTO.getUserEmail();
    }

    public Trip(Long id, Timestamp initTime, Timestamp endTime,
                Double kms, Boolean ended, Double priceService,
                String scooter, String userEmail, PauseTrip pause) {
        this.id = id;
        this.initTime = initTime;
        this.endTime = endTime;
        this.kms = kms;
        this.ended = ended;
        this.tariff = new Tariff(priceService,1L);
        if(pause != null) {
            this.pause = new PauseTrip(pause.getTimePause(),pause.getInitPause(),pause.getEndPause());
        }
        else {
            this.pause = null;
        }
        this.timer = null;
        this.tariffExtra = null;
        this.licenseScooter = scooter;
        this.userEmail = userEmail;
    }

}
