package com.example.travello.repository;

import com.example.travello.entity.Trip;
import com.example.travello.entity.TripStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TripRepository extends CrudRepository<Trip, Long> {

    @Query( value = "select * from trips t where t.account_id = ?1", nativeQuery = true)
    List<Trip> findTripByOwner(Long id);

    @Query(value = "update trips set status = ?2 where id = ?1", nativeQuery = true)
    TripStatus updateTripStatus(Long tripId, int status);

    @Query(value = "select * from trips t where t.id = ?1 and t.status = ?2", nativeQuery = true)
    List<Trip> findByOwnerAndStatus(Long tripId, int status);
}
