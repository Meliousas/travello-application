package com.example.travello.controller;

import com.example.travello.entity.Account;
import com.example.travello.entity.Trip;
import com.example.travello.service.AccountService;
import com.example.travello.service.TripService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/trip")
public class TripController {

    static Logger logger = LoggerFactory.getLogger(TripController.class);

    @Autowired
    TripService tripService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<Trip>> getAllTrips(){
        List<Trip> trips = tripService.getTrips();

        logger.info("Requesting all trips list: {} objects", trips.size());
        return new ResponseEntity<>(trips, HttpStatus.OK);
    }

}
