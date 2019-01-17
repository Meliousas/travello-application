package com.example.travello.trip.card;

import com.example.travello.account.Account;
import com.example.travello.account.AccountRepository;
import com.example.travello.trip.Trip;
import com.example.travello.trip.TripRepository;
import com.example.travello.trip.TripStatus;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static com.example.travello.account.AccountTest.asJsonString;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(value = SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CardTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    TripRepository tripRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CardRepository cardRepository;

    @Before
    public void clear(){
        tripRepository.deleteAll();
        accountRepository.deleteAll();
        cardRepository.deleteAll();
    }

    @Test
    public void shouldCreateCardForTrip() throws Exception {
        Card card = createCard(0L);
        this.mockMvc
                .perform(post("/api/card/trip/{id}/add" , card.getTrip().getId().toString())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(card)))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteCard() throws Exception{
        Card card = createCard(1L);

        this.mockMvc
                .perform(delete("/api/card/{id}", card.getId())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(card)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldRetrieveAllCardsForTrip() throws Exception {
        Card card1 = createCard(22L);
        createCard(55L);
        createCard(555L);

        this.mockMvc
                .perform(get("/api/card/trip/{id}", card1.getTrip().getId().toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void shouldEditExistingCard() throws Exception {
        Card cardBefore = createCard(99L);
        Card cardAfter = new Card(5L, null , "title", "edited", null, null, CardType.NOTE);

        this.mockMvc
                .perform(put("/api/card/trip/{tripId}/{id}", cardBefore.getTrip().getId(), cardBefore.getId())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(cardAfter)))
                .andExpect(jsonPath("$.description", Matchers.is("edited")))
                .andExpect(status().isOk());
    }

    private Trip createTrip(){
        Account a = new Account("user", "password","email", false, false, true);
        accountRepository.save(a);
        return new Trip(0L, a,"title", TripStatus.PRIVATE, null,
                null,  LocalDate.now(), LocalDate.now(), null, null,
                false, 100, 10 );
    }

    private Card createCard(Long id){
        Trip t = createTrip();
        tripRepository.save(t);
        Card card = new Card(id, t, "title", null, null, null, CardType.NOTE);
        cardRepository.save(card);

        return card;
    }


}
