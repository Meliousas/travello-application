package com.example.travello.card;

import com.example.travello.entity.Account;
import com.example.travello.entity.Card;
import com.example.travello.entity.Trip;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(value = SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CardTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void should_create_trip_with_all_data() throws Exception {
        Card card = new Card(0L, createTrip(), "title", "desc", LocalDate.now(), "IMG");
        this.mockMvc
                .perform(post("/api/trip/add")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(card)))
                .andExpect(status().isOk());
    }

    @Test
    public void should_create_card_with_only_title_and_trip() throws Exception{
        Card card = new Card(1L, createTrip(), "title", null, null, null);
        this.mockMvc
                .perform(post("/api/trip/add")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(card)))
                .andExpect(status().isOk());
    }

    private Trip createTrip(){
        return new Trip(0L, new Account(),"title", null,
                0.2, null, false, LocalDate.now(),
                LocalDate.now());
    }


}
