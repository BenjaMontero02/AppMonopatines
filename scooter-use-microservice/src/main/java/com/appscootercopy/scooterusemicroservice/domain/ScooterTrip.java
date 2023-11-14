package com.appscootercopy.scooterusemicroservice.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class ScooterTrip {

    @EmbeddedId
    private ScooterTripId id;

    public ScooterTrip(ScooterTripId id) {
        this.id = id;
    }

}
