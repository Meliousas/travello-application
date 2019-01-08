package com.example.travello.service;

import com.example.travello.entity.Rating;
import com.example.travello.entity.Trip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Predicate;

@Service
public class RatingService {

    private static Logger logger = LoggerFactory.getLogger(RatingService.class);

    @Autowired
    TripService tripService;

    public Double addRating(Trip trip, int rate, long userId){
        Rating rating = Rating.builder().tripId(trip.getId()).rate((double) rate).userId(userId).build();

        trip.addRating(rating);
        trip.setSumRatings(trip.getSumRatings() + rate);
        trip.setSumVotes(trip.getSumVotes() + 1);

        logger.info("Calculating rating for trip: {}. Rating added: {}", trip.getId(), rate);
        return getRating(trip.getId());
    }

    public Double getRating(long tripId) {
        Optional<Trip> trip = tripService.getTripById(tripId);

        if (trip.get().getSumVotes() == 0) {
            return Double.NaN;
        }
        else {
           return trip.get().getSumRatings() / trip.get().getSumVotes();
        }
    }

    public boolean canVote(long uId, long tripId){
        return !tripService
                .getTripById(tripId)
                .map(Trip::getRatings)
                .map(ratings -> ratings.iterator().next().getUserId())
                .filter(Predicate.isEqual(uId)).isPresent();
    }
}
