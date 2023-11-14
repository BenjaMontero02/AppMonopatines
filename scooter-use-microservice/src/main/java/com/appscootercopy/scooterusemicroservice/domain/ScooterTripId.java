package com.appscootercopy.scooterusemicroservice.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
public class ScooterTripId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "scooter_id")
    private Scooter idScooter;
    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip idTrip;

    public ScooterTripId(Scooter idScooter, Trip idTrip) {
        super();
        this.idScooter=idScooter;
        this.idTrip=idTrip;
    }
}
