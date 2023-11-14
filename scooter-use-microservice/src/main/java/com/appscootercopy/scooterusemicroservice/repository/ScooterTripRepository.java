package com.appscootercopy.scooterusemicroservice.repository;

import com.appscootercopy.scooterusemicroservice.domain.ScooterTrip;
import com.appscootercopy.scooterusemicroservice.domain.ScooterTripId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScooterTripRepository extends JpaRepository<ScooterTrip, ScooterTripId> {

}
