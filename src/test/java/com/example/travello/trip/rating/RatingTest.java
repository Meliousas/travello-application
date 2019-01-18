package com.example.travello.trip.rating;

import com.example.travello.account.Account;
import com.example.travello.account.AccountRepository;
import com.example.travello.trip.Trip;
import com.example.travello.trip.TripRepository;
import com.example.travello.trip.TripStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(value = SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RatingTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TripRepository tripRepository;

    @Before
    public void init(){
        tripRepository.deleteAll();
        accountRepository.deleteAll();
    }

    @Test
    public void shouldRetrieveTripRating() throws Exception {
        Trip t = createTrip();
        t.setSumRatings(12);
        t.setSumVotes(6);
        Trip created = tripRepository.save(t);

        this.mockMvc.perform(get("/api/trip/{id}/rating", created.getId()))
                .andExpect(status().isOk())
                // sumRatings(12)/sumVotes(6) = rating (2)
                .andExpect(content().string(String.valueOf(2.0)));
    }

    private Trip createTrip(){
        Account a = new Account("user", "password","email", false, false, true);
        accountRepository.save(a);
        return new Trip(0L, a,"title", TripStatus.PRIVATE, null,
                null,  LocalDate.now(), LocalDate.now(), null, null,
                false, 20, 5 );
    }
}