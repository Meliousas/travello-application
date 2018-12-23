package com.example.travello.service;

import com.example.travello.entity.Trip;
import com.example.travello.entity.TripStatus;
import com.example.travello.repository.TripRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TripService {

    @Autowired
    TripRepository tripRepository;

    public List<Trip> getTrips() {
       return Lists.newArrayList(tripRepository.findAll());
    }

    public Optional<Trip> getTripById(Long id){ return tripRepository.findById(id);}

    public List<Trip> getTripsByOwnerId(Long id) {
        return tripRepository.findTripByOwner(id);
    }

    public Trip createTrip(Trip trip){
        return tripRepository.save(trip);
    }

    public void changeTripStatus(Long tripId, int status){
       tripRepository.updateTripStatus(tripId, status);
    }

    public void deleteTrip(Long id) {
       tripRepository.deleteById(id);
    }

    public List<Trip> getUserTripsByStatus(long id, int status) {
        return tripRepository.findByOwnerAndStatus(id, status);
    }

    public Trip editTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    public List<String> getCountriesForTrip(long id) {
        return tripRepository.findCountriesForTrip(id);
    }
}
