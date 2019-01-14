package com.example.travello.trip.card;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CardRepository extends CrudRepository<Card, Long> {

    @Query( value = "select * from cards c where c.trip_id = ?1", nativeQuery = true)
    List<Card> findByTripId(Long id);

    @Query( value = "select * from cards c where c.trip_id = ?1 and c.type = 0", nativeQuery = true)
    List<Card> findByCardsType(Long id);

    @Query( value = "select * from cards c where c.trip_id = ?1 and c.type = 1", nativeQuery = true)
    List<Card> findByNotesType(Long id);
}
