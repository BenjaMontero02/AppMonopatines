package com.appscooter.tripmicroservice.repositories;

import com.appscooter.tripmicroservice.domain.Tariff;
import com.appscooter.tripmicroservice.repositories.interfaces.ProfitsByMonthsInYearInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TariffRepository extends JpaRepository<Tariff, Long> {

    Boolean existsByType(Long type);

    @Query(value = "SELECT xyear, SUM(totalProfits) AS totalProfits FROM ( " +
            "SELECT EXTRACT(year from t.init_time) as xyear, SUM(f.cost) AS totalProfits " +
            "FROM trip t JOIN tariff f on t.tariff_id = f.id " +
            "WHERE EXTRACT(YEAR FROM t.init_time) =:yearSearch " +
            "AND (EXTRACT(MONTH FROM t.init_time) BETWEEN :firstMonth AND :lastMonth) " +
            "\tGROUP BY xyear " +
            "UNION " +
            "SELECT EXTRACT(YEAR FROM t.init_time) as xyear, SUM(ff.cost) AS totalProfits " +
            "FROM trip t JOIN tariff ff on t.tariff_extra_id = ff.id " +
            "WHERE EXTRACT(year FROM t.init_time) =:yearSearch " +
            "AND (EXTRACT(MONTH FROM t.init_time) BETWEEN :firstMonth AND :lastMonth) " +
            "\tGROUP BY xyear " +
            ") AS subQuery " +
            "GROUP BY xyear ORDER BY totalProfits ASC;", nativeQuery = true)
    List<ProfitsByMonthsInYearInterface> findProfitsByMonthsInYear(@Param("firstMonth") Long month1,
                                                                   @Param("lastMonth") Long month2, @Param("yearSearch") Long yearSearch);



}
