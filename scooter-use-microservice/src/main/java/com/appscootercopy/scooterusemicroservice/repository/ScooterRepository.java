package com.appscootercopy.scooterusemicroservice.repository;

import com.appscootercopy.scooterusemicroservice.domain.Scooter;
import com.appscootercopy.scooterusemicroservice.repository.interfaces.ScootersAvailablesInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScooterRepository extends JpaRepository<Scooter, Long> {

    Scooter findByLicensePLate(String licensePlate);

    Boolean existsByLicensePLate(String licensePlate);

    @Query("SELECT COUNT(s.id) AS countScooters FROM Scooter s WHERE s.available =:state")
    ScootersAvailablesInterface findCountScootersAvailables(@Param("state") Boolean state);

    @Query("SELECT s FROM Scooter s JOIN fetch s.ubication")
    List<Scooter> findAllfetchingUbication();

    @Query("SELECT s FROM Scooter s " +
            "JOIN fetch s.ubication " +
            "WHERE (s.ubication.y between (:y - :yLimit) and (:y + :yLimit)) " +
            "AND (s.ubication.x between (:x - :xLimit) and (:x + :xLimit))")
    List<Scooter> findAllCloseToMe(@Param("x") Double x, @Param("y") Double y, @Param("yLimit") Double yLimit, @Param("xLimit") Double xLimit);

}
