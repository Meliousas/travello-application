package com.example.travello.controller;

import com.example.travello.entity.Card;
import com.example.travello.entity.Trip;
import com.example.travello.service.CardService;
import com.example.travello.service.TripService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/card")
public class CardController {

    private static Logger logger = LoggerFactory.getLogger(CardController.class);

    @Autowired
    TripService tripService;

    @Autowired
    CardService cardService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Card>> getAllCards(){
        List<Card> cards = cardService.getAllCards();

        logger.info("Requesting all cards list: {} cards found", cards.size());
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Card> getCardById(@PathVariable Long id){
        Optional<Card> card = cardService.getCardById(id);

        if(!card.isPresent()){
            logger.info("Card with id: {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            logger.info("Card with id: {} found", id);
            return new ResponseEntity<>(card.get(), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteCardById(@PathVariable Long id){
        logger.info("Deleting card with id: " + id);

        Optional<Card> card = cardService.getCardById(id);

        if (!card.isPresent()) {
            logger.info("Unable to delete. Card with id: " + id + " not found");
            return new ResponseEntity<Card>(HttpStatus.NOT_FOUND);
        }

        cardService.deleteCardById(id);
        return new ResponseEntity<>(card.get(), HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/trip/{id}/add", method = RequestMethod.POST)
    public ResponseEntity<Card> createTrip(@PathVariable long id, @RequestBody Card card){

        Optional<Trip> trip = tripService.getTripById(id);

        if(!trip.isPresent()){
            logger.info("Card creation failed. Trip does not exist.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        card.setTrip(trip.get());
        Card createdCard = cardService.createCard(card);

        logger.info("Card with id: {} created.", card.getId());
        return new ResponseEntity<>(createdCard, HttpStatus.OK);
    }


    @RequestMapping(value = "/trip/{tripId}", method = RequestMethod.GET)
    public ResponseEntity<List<Card>> getCardsForTrip(@PathVariable Long tripId){

        List<Card> cards = cardService.getCardsForTrip(tripId);

        logger.info("Requesting all cards for trip id: {}. {} cards found", cards.size());
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @RequestMapping(value = "/trip/{tripId}/cards", method = RequestMethod.GET)
    public ResponseEntity<List<Card>> getCardsTypeForTrip(@PathVariable Long tripId){

        List<Card> cards = cardService.getCardsTypeForTrip(tripId);

        logger.info("Requesting all cards for trip id: {}. {} cards found", cards.size());
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @RequestMapping(value = "/trip/{tripId}/notes", method = RequestMethod.GET)
    public ResponseEntity<List<Card>> getNotesTypeForTrip(@PathVariable Long tripId){

        List<Card> cards = cardService.getNotesTypeForTrip(tripId);

        logger.info("Requesting all notes for trip id: {}. {} notes found", cards.size());
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Card> editCard(@PathVariable long id, @RequestBody Card card){

        Optional<Card> foundCard = cardService.getCardById(id);
        card.setId(id);
        if(!foundCard.isPresent()){
            Card editedCard = cardService.createCard(card);
            logger.info("Card with id: {} doesn't exist. Creating new one.", card.getId());
            return new ResponseEntity<>(editedCard, HttpStatus.OK);
        }

        Card editedCard = cardService.editCard(card);

        logger.info("Card with id: {} successfully edited.", card.getId());
        return new ResponseEntity<>(editedCard, HttpStatus.OK);
    }

}
