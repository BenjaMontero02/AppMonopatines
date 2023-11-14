package com.appscootercopy.scooterusemicroservice.repository;

import com.appscootercopy.scooterusemicroservice.domain.ScooterTrip;
import com.appscootercopy.scooterusemicroservice.domain.ScooterTripId;
import com.appscootercopy.scooterusemicroservice.repository.interfaces.ReportInterface;
import com.appscootercopy.scooterusemicroservice.repository.interfaces.ScootersByTripsAndYearInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScooterTripRepository extends JpaRepository<ScooterTrip, ScooterTripId> {
    @Query("SELECT st FROM ScooterTrip st WHERE st.id.idScooter.id =:idScooter")
    List<ScooterTrip> findAllById_IdScooterId(@Param("idScooter") Long id);

    @Query("SELECT st FROM ScooterTrip st WHERE st.id.idTrip.id =:tripId")
    ScooterTrip findById_IdTrip_Id(@Param("tripId") Long id);

    @Query("SELECT s.id AS id, s.licensePLate AS licensePlate, s.available AS available" +
            ", COUNT(st.id.idTrip.id) AS countTrips" +
            ", SUM(t.kms) AS kms " +
            "FROM ScooterTrip st " +
            "JOIN st.id.idScooter s " +
            "JOIN st.id.idTrip t " +
            "GROUP BY id, licensePlate, available " +
            "ORDER BY kms DESC")
    List<ReportInterface> findAllByKms();

    @Query("SELECT st.id.idScooter.id AS id, s.licensePLate AS licensePlate, s.available AS available" +
            ", COUNT(st.id.idTrip.id) AS countTrips" +
            ", SUM(t.kms) AS kms " +
            "FROM ScooterTrip st " +
            "JOIN st.id.idScooter s " +
            "JOIN st.id.idTrip t " +
            "WHERE t.pause is not null " +
            "GROUP BY id, licensePlate, available " +
            "ORDER BY kms DESC")
    List<ReportInterface> findAllByTimeCcPauses();

    @Query("SELECT st.id.idScooter.id AS id, s.licensePLate AS licensePlate, s.available AS available" +
            ", COUNT(st.id.idTrip.id) AS countTrips" +
            ", SUM(t.kms) AS kms " +
            "FROM ScooterTrip st " +
            "JOIN st.id.idScooter s " +
            "JOIN st.id.idTrip t " +
            "WHERE t.pause is null " +
            "GROUP BY id, licensePlate, available " +
            "ORDER BY kms DESC")
    List<ReportInterface> findAllByTimeWithoutPauses();

    @Query("SELECT s.licensePLate AS licensePlate, s.available AS available, " +
            "COUNT(st.id.idScooter.id) AS countTrips, " +
            "extract(YEAR FROM t.initTime) AS year " +
            "FROM ScooterTrip st " +
            "JOIN st.id.idScooter s " +
            "JOIN st.id.idTrip t " +
            "WHERE extract(YEAR FROM t.initTime) =:year " +
            "GROUP BY s.licensePLate, s.available, year " +
            "HAVING COUNT(st.id.idScooter.id) >:minTrips " +
            "ORDER BY countTrips DESC")
    List<ScootersByTripsAndYearInterface> findAllScooterByTripsAndYear(@Param("minTrips") Long minTrips,
                                                                       @Param("year") Long year);

}
