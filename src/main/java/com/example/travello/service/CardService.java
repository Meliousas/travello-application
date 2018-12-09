package com.example.travello.service;

import com.example.travello.entity.Card;
import com.example.travello.repository.CardRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;

    public List<Card> getCardsForTrip(Long id){
      return cardRepository.findByTripId(id);
    }

    public Optional<Card> getCardById(Long id) {
        return cardRepository.findById(id);
    }

    public List<Card> getAllCards() {
       return Lists.newArrayList(cardRepository.findAll());
    }

    public void deleteCardById(Long id) {
        cardRepository.deleteById(id);
    }

    public Card createCard(Card card) {
        return cardRepository.save(card);
    }
}
