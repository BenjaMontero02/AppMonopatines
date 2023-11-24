package com.appscooter.tripmicroservice.repositories;

import com.appscooter.tripmicroservice.domain.Trip;
import com.appscooter.tripmicroservice.repositories.interfaces.ReportInterface;
import com.appscooter.tripmicroservice.repositories.interfaces.ScootersByTripsAndYearInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip,Long> {

    @Query("SELECT t FROM Trip t WHERE t.licenseScooter =:license")
    List<Trip> findAllByLicenseScooter(@Param("license") String id);

    /*devuelve un reporte de cada scooter
    con su cantidad de viajes y total de kms recorridos*/
    @Query("SELECT t.licenseScooter AS licenseScooter" +
            ", COUNT(t.id) AS countTrips" +
            ", SUM(t.kms) AS kms " +
            "FROM Trip t " +
            "GROUP BY licenseScooter " +
            "ORDER BY kms DESC")
    List<ReportInterface> findAllByKms();


    void deleteAllByLicenseScooter(String licenseScooter);

    @Query("SELECT t.licenseScooter AS licenseScooter" +
            ", COUNT(t.id) AS countTrips" +
            ", SUM(t.kms) AS kms " +
            "FROM Trip t " +
            "WHERE t.pause is not null " +
            "GROUP BY licenseScooter " +
            "ORDER BY kms DESC")
    List<ReportInterface> findAllByTimeCcPauses();

    @Query("SELECT t.licenseScooter AS licenseScooter" +
            ", COUNT(t.id) AS countTrips" +
            ", SUM(t.kms) AS kms " +
            "FROM Trip t " +
            "WHERE t.pause is null " +
            "GROUP BY licenseScooter " +
            "ORDER BY kms DESC")
    List<ReportInterface> findAllByTimeWithoutPauses();

    @Query("SELECT t.licenseScooter as licenseScooter, " +
            "COUNT(t.id) AS countTrips, " +
            "extract(YEAR FROM t.initTime) AS year " +
            "FROM Trip t " +
            "WHERE extract(YEAR FROM t.initTime) =:year " +
            "GROUP BY t.licenseScooter, year " +
            "HAVING COUNT(t.id) >:minTrips " +
            "ORDER BY countTrips DESC")
    List<ScootersByTripsAndYearInterface> findAllScooterByTripsAndYear(@Param("minTrips") Long minTrips, @Param("year") Long year);

}
