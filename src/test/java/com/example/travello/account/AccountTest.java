package com.example.travello.account;


import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(value = SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    AccountRepository accountRepository;

    @Before
    public void clear() {
        accountRepository.deleteAll();
    }

    @Test
    public void shouldCreateAccount() throws Exception {
        Account account = new Account("user", "password","email", false, false, true);

        this.mockMvc
                .perform(post("/api/account/register")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(account)))
                        .andExpect(status().isCreated());
    }

    @Test
    public void shouldDeleteAccount() throws Exception {
        Account account = new Account("testUser", "firstPass", "email5", false,  false, true);
        Account a = accountRepository.save(account);
        Long createdId = a.getId();

        this.mockMvc
                .perform(delete("/api/account/{id}", createdId.toString())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(account)))
                .andExpect(status().isNoContent());

        this.mockMvc
                .perform(get("/api/account/{id}", createdId.toString()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldGetAccountById() throws Exception{
        Account account = new Account("testUser", "firstPass", "email5", false,  false, true);
        Account a = accountRepository.save(account);
        Long createdId = a.getId();

        this.mockMvc
                .perform(get("/api/account/{id}", createdId.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void shouldGetAllAccounts() throws Exception {
        Account account = new Account("firstUser", "firstPass", "email1", false,  false, true);
        Account account2 = new Account("secondUser", "secondPass","email2", false,  false, true);

        List<Account> accounts = Arrays.asList(account, account2);
        createAccounts(accounts);

        mockMvc.perform(get("/api/account/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void shouldChangeUserStatus() throws Exception {
        Account account = new Account("user", "password","email", false, false, true);
        createAccounts(Collections.singletonList(account));

        mockMvc.perform(put("/api/account/1/status/0"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/account/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.active", Matchers.is(false)));
    }

    private void createAccounts(List<Account> accounts) throws Exception {
        for(Account a : accounts){
            this.mockMvc.perform(post("/api/account/register")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(asJsonString(a)))
                    .andExpect(status().isCreated());
        }
    }

    public static String asJsonString(Object obj){
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String json = mapper.writeValueAsString(obj);
            System.out.println(json);
            return json;
        } catch(Exception ex){ throw new RuntimeException(); }
    }
}
