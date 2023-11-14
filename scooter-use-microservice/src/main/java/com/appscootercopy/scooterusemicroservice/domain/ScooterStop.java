package com.appscootercopy.scooterusemicroservice.domain;

import com.appscootercopy.scooterusemicroservice.service.dto.scooterStop.request.ScooterStopRequestDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class ScooterStop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Ubication ubication;

    public ScooterStop(ScooterStopRequestDTO requestDTO) {
        this.ubication = requestDTO.getUbication();
    }

    public ScooterStop(Ubication ubication) {
        this.ubication = ubication;
    }

}
