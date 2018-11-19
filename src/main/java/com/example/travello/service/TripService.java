package com.example.travello.service;

import com.example.travello.entity.Trip;
import com.example.travello.repository.TripRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TripService {

    @Autowired
    TripRepository tripRepository;

    public List<Trip> getTrips() {
       return Lists.newArrayList(tripRepository.findAll());
    }

    public Optional<Trip> getTripById(Long id){ return tripRepository.findById(id);}

    public List<Trip> getTripsByTripOwner(Long id) {
        return tripRepository.findByTripOwner(id);
    }

    public Trip createAccount(Trip trip){
        tripRepository.save(trip);
        return trip;
    }
}
