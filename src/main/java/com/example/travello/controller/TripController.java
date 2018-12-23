package com.example.travello.controller;

import com.example.travello.entity.Account;
import com.example.travello.entity.Trip;
import com.example.travello.entity.TripStatus;
import com.example.travello.service.AccountService;
import com.example.travello.service.TripService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/trip")
public class TripController {

   private static Logger logger = LoggerFactory.getLogger(TripController.class);

    @Autowired
    TripService tripService;

    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Trip>> getAllTrips(){
        List<Trip> trips = tripService.getTrips();

        logger.info("Requesting all trips list: {} trips found", trips.size());
        return new ResponseEntity<>(trips, HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Trip> getTripById(@PathVariable Long id){
        Optional<Trip> trip = tripService.getTripById(id);

        if(!trip.isPresent()){
            logger.info("Trip with id: {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            logger.info("Trip with id: {} found", id);
            return new ResponseEntity<>(trip.get(), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteTripById(@PathVariable Long id){
        logger.info("Deleting trip with id: " + id);

        Optional<Trip> trip = tripService.getTripById(id);

        if (!trip.isPresent()) {
            logger.info("Unable to delete. Trip with id: " + id + " not found");
            return new ResponseEntity<Trip>(HttpStatus.NOT_FOUND);
        }

        tripService.deleteTrip(id);
        return new ResponseEntity<>(trip.get(), HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/user/{id}/add", method = RequestMethod.POST)
    public ResponseEntity<Trip> createTrip(@PathVariable long id, @RequestBody Trip trip){

        Optional<Account> account = accountService.getAccountById(id);

        if(!account.isPresent()){
            logger.info("Trip creation failed. User does not exist.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        trip.setAccount(account.get());
        Trip createdTrip = tripService.createTrip(trip);

        logger.info("Trip with id: {} created for user: {}", trip.getId(), account.get().getUsername());
        return new ResponseEntity<>(createdTrip, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public ResponseEntity<List<Trip>> getTripsByOwnerId(@PathVariable Long userId){
        List<Trip> trips = tripService.getTripsByOwnerId(userId);

        logger.info("Requesting trips for user with id: {}. Found {} trips", userId, trips.size());
        return new ResponseEntity<>(trips, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/status/{status}", method = RequestMethod.PUT)
    public ResponseEntity<TripStatus> putTripStatus(@PathVariable Long id, @PathVariable int status){
        tripService.changeTripStatus(id, status);

        logger.info("Updating trip with id: {} with status: {}", id, status);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{id}/status/{status}", method = RequestMethod.GET)
    public ResponseEntity<List<Trip>> getUserTripsWithGivenStatus(@PathVariable long id, @PathVariable int status){
        List<Trip> trips = tripService.getUserTripsByStatus(id, status);

        logger.info("Requesting trips for user with id: {}, status: {}. {} trips found", id, status, trips.size());
        return new ResponseEntity<>(trips, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{userId}/id/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Trip> editTrip(@PathVariable long userId, @PathVariable long id, @RequestBody Trip trip){

        Optional<Account> account = accountService.getAccountById(userId);
        if(!account.isPresent()){
            logger.info("Trip creation failed. User does not exist.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        trip.setAccount(account.get());
       
        Optional<Trip> foundTrip = tripService.getTripById(id);
        trip.setId(id);
        if(!foundTrip.isPresent()){
            Trip editedTrip = tripService.editTrip(trip);
            logger.info("Trip with id: {} doesn't exist. Creating new one.", trip.getId());
            return new ResponseEntity<>(editedTrip, HttpStatus.OK);
        }

        Trip editedTrip = tripService.editTrip(trip);

        logger.info("Trip with id: {} successfully edited.", trip.getId());
        return new ResponseEntity<>(editedTrip, HttpStatus.OK);
    }
                                        
    @RequestMapping(value = "/{id}/countries", method = RequestMethod.GET)
      public ResponseEntity<List<String>> getCountriesForTrip(@PathVariable long id){
        List<String> countries = tripService.getCountriesForTrip(id);

        logger.info("Requesting list of countries for trip with id: {}. {} countries found", id, countries.size());
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }
//
//    @RequestMapping(value = "/{id}/countries", method = RequestMethod.PUT)
//    public ResponseEntity<List<String>> insertCountriesForTrip(@PathVariable long id,  @RequestBody Set<String> countries){
//        List<String> countries = tripService.getCountriesForTrip(id);
//
//        logger.info("Requesting list of countries for trip with id: {}. {} countries found", id, countries.size());
//        return new ResponseEntity<>(countries, HttpStatus.OK);
//    }


}
