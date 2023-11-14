package com.appscootercopy.scooterusemicroservice.repository;
import com.appscootercopy.scooterusemicroservice.domain.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip,Long> {

}
