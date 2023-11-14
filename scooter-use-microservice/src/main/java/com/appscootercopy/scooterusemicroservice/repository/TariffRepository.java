package com.appscootercopy.scooterusemicroservice.repository;

import com.appscootercopy.scooterusemicroservice.domain.Tariff;
import com.appscootercopy.scooterusemicroservice.repository.interfaces.ProfitsByMonthsInYearInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TariffRepository extends JpaRepository<Tariff, Long> {

    Boolean existsByType(Long type);

    @Query(value = "SELECT xmonth, xyear, SUM(totalProfits) AS totalProfits " +
            "FROM (SELECT EXTRACT(MONTH FROM t.init_time) AS xmonth, " +
            "EXTRACT(year from t.init_time) as xyear, " +
            "SUM(f.cost) AS totalProfits " +
            "FROM trip t JOIN tariff f on t.tariff_id = f.id " +
            "WHERE EXTRACT(YEAR FROM t.init_time) =:yearSearch " +
            "GROUP BY xmonth, xyear " +
            "UNION " +
            "SELECT EXTRACT(MONTH FROM t.init_time) AS xmonth, " +
            "EXTRACT(YEAR FROM t.init_time) AS xyear, " +
            "SUM(ff.cost) AS totalProfits " +
            "FROM trip t JOIN tariff ff on t.tariff_extra_id = ff.id " +
            "WHERE EXTRACT(year FROM t.init_time) =:yearSearch " +
            "GROUP BY xmonth, xyear) AS subQuery " +
            "GROUP BY xmonth, xyear ORDER BY xmonth ASC", nativeQuery = true)
    List<ProfitsByMonthsInYearInterface> findProfitsByMonthsInYear(@Param("yearSearch") Long yearSearch);



}
