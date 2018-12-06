package com.example.travello.repository;

import com.example.travello.entity.Card;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CardRepository extends CrudRepository<Card, Long> {

    @Query( value = "select * from cards c where c.trip_id = ?1", nativeQuery = true)
    List<Card> findByTripId(Long id);
}
