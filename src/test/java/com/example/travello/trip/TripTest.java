package com.example.travello.trip;

import com.example.travello.account.Account;
import com.example.travello.account.AccountRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(value = SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TripTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TripRepository tripRepository;

    @Before
    public void setup(){
        tripRepository.deleteAll();
        accountRepository.deleteAll();

    }

    @Test
    public void shouldAddTripForUser() throws Exception {
        Account a = createAccount();

        mockMvc.perform(post("/api/trip/user/{id}/add", a.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(createTrip())))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteTripById() throws Exception {
        Trip t = createTrip();
        t.setAccount(createAccount());
        tripRepository.save(t);

        mockMvc.perform(delete("/api/trip/{id}", t.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldGetTripById() throws Exception {
        Trip t = createTrip();
        t.setAccount(createAccount());
        Trip created = tripRepository.save(t);

        mockMvc.perform(get("/api/trip/{id}", created.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldChangeTripStatus() throws Exception {
        Trip t = createTrip();
        t.setAccount(createAccount());
        Trip created = tripRepository.save(t);

        mockMvc.perform(put("/api/trip/{id}/status/{status}", created.getId(), String.valueOf(TripStatus.BLOCKED.ordinal())))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/trip/{id}", created.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", Matchers.is(String.valueOf(TripStatus.BLOCKED))));

    }

    @Test
    public void shouldGetTripOwner() throws Exception {
        Account a = createAccount();
        Trip t = createTrip();
        t.setAccount(a);
        Trip created = tripRepository.save(t);

        mockMvc.perform(get("/api/trip/{id}/owner", created.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(t.getAccount().getId().intValue())));
    }

    private Account createAccount(){
        Account account = new Account("firstUser", "firstPass", "email1", false,  false, true);
        return accountRepository.save(account);
    }

    private Trip createTrip(){
        return new Trip(0L, null,"title", TripStatus.PRIVATE, null,
                null,  LocalDate.now(), LocalDate.now(), null, null,
                false, 100, 10 );
    }
}
