package com.appscootercopy.scooterusemicroservice.domain;

import com.appscootercopy.scooterusemicroservice.service.dto.ubication.request.UbicationRequestDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Ubication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Double x;
    @Column(nullable = false)
    private Double y;

    public Ubication(UbicationRequestDTO requestDTO) {
        this.x = requestDTO.getX();
        this.y = requestDTO.getY();
    }

    public Ubication(Double x, Double y) {
        this.x = x;
        this.y = y;
    }
}
