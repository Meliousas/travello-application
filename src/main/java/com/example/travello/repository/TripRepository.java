package com.example.travello.repository;

import com.example.travello.entity.Trip;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TripRepository extends CrudRepository<Trip, Long> {

    @Query( value = "select * from trips t where t.account_id = ?1",
            nativeQuery = true)
    List<Trip> findByTripOwner(Long id);
}
