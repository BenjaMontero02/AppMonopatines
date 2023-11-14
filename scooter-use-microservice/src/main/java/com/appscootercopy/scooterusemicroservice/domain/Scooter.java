package com.appscootercopy.scooterusemicroservice.domain;

import com.appscootercopy.scooterusemicroservice.service.dto.scooter.request.ScooterRequestDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Scooter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "license_plate", unique = true)
    private Long licensePLate;
    @Column(nullable = false)
    private Boolean available;
    @ManyToOne(fetch = FetchType.LAZY)
    private Ubication ubication;

    public Scooter(ScooterRequestDTO requestDTO) {
        this.licensePLate = requestDTO.getLicensePlate();
        this.available = requestDTO.getAvailable();
        this.ubication = requestDTO.getUbication();
    }

    public Scooter(Long licensePLate, Boolean available, Ubication ubication) {
        this.licensePLate = licensePLate;
        this.available = available;
        this.ubication = ubication;
    }
}
