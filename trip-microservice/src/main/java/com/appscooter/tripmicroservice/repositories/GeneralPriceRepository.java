package com.appscooter.tripmicroservice.repositories;

import com.appscooter.tripmicroservice.domain.GeneralPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;

public interface GeneralPriceRepository extends JpaRepository<GeneralPrice, Long> {

    @Query("select g from GeneralPrice g where g.dateValidity =:validity")
    GeneralPrice findByDateValidity(@Param("validity") Timestamp validity);

    @Query("select g from GeneralPrice g where g.dateValidity >:date")
    GeneralPrice findByLastDateValidity(@Param("date") Timestamp date);

    @Query("select g from GeneralPrice g where g.current =:current")
    GeneralPrice findByCurrent(@Param("current") Boolean current);
}
