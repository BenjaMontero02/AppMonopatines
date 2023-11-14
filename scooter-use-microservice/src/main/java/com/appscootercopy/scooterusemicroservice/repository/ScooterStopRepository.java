package com.appscootercopy.scooterusemicroservice.repository;

import com.appscootercopy.scooterusemicroservice.domain.ScooterStop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScooterStopRepository extends JpaRepository<ScooterStop, Long> {
}
