package com.example.travello.repository;

import com.example.travello.entity.Trip;
import com.example.travello.entity.TripStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

public interface TripRepository extends CrudRepository<Trip, Long> {

    @Query(value = "select * from trips t where t.account_id = ?1", nativeQuery = true)
    List<Trip> findTripByOwner(Long id);

    @Transactional
    @Modifying
    @Query(value = "update trips set status = ?2 where id = ?1", nativeQuery = true)
    void updateTripStatus(Long tripId, int status);

    @Query(value = "select * from trips t where t.account_id = ?1 and t.status = ?2", nativeQuery = true)
    List<Trip> findByOwnerAndStatus(Long tripId, int status);

    @Query(value = "select country from trip_countries c where c.trip_id = ?1", nativeQuery = true)
    List<String> findCountriesForTrip(long id);
}
