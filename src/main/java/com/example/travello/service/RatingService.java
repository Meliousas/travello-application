package com.example.travello.service;

import com.example.travello.entity.Rating;
import com.example.travello.entity.Trip;
import com.example.travello.repository.RatingRepository;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    private static Logger logger = LoggerFactory.getLogger(RatingService.class);

    @Autowired
    TripService tripService;

    @Autowired
    RatingRepository ratingRepository;

    public Double addRating(Trip trip, int rate, long userId){
        Rating rating = Rating.builder().tripId(trip.getId()).rate((double) rate).userId(userId).build();
        ratingRepository.save(rating);

        trip.setSumRatings(trip.getSumRatings() + rate);
        trip.setSumVotes(trip.getSumVotes() + 1);

        logger.info("Calculating rating for trip: {}. Rating added: {}", trip.getId(), rate);
        tripService.editTrip(trip);
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
        Iterable<Rating> ratingIterable = ratingRepository.findAll();

        if(ratingIterable.spliterator().estimateSize() == 0){
            return true;
        }

        List<Rating> ratings =  Lists.newArrayList(ratingIterable);
        Optional<Rating> result =
                ratings.stream().filter(obj -> obj.getUserId() == uId && obj.getTripId() == tripId).findFirst();

        return !result.isPresent();
    }

}
