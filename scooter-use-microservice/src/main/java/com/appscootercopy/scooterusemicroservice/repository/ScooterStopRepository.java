package com.appscootercopy.scooterusemicroservice.repository;

import com.appscootercopy.scooterusemicroservice.domain.Scooter;
import com.appscootercopy.scooterusemicroservice.domain.ScooterStop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScooterStopRepository extends JpaRepository<ScooterStop, Long> {

    ScooterStop findByUbicationId(Long ubicationId);

    @Query("SELECT ss FROM ScooterStop ss JOIN fetch ss.ubication")
    List<ScooterStop> findAllfetchingUbication();

    @Query("SELECT ss FROM ScooterStop ss WHERE ss.ubication.x =:x AND ss.ubication.y =:y")
    ScooterStop existsByUbicationXAndUbicationY(@Param("x") Double x, @Param("y") Double y);

    @Query("SELECT ss FROM ScooterStop ss WHERE ss.ubication.x =:x AND ss.ubication.y =:y")
    ScooterStop findByUbicationXAndUbicationY(@Param("x") Double x, @Param("y") Double y);

}
