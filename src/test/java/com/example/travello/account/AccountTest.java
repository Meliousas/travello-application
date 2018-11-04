package com.example.travello.account;


import com.example.travello.entity.Account;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(value = SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void createAccount() throws Exception {
        Account account = new Account("user", "password");

        this.mockMvc
                .perform(post("/api/account/register")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(account)))
                        .andExpect(status().isOk());
    }

    @Test
    public void getAllAccounts() throws Exception {
        Account account = new Account("firstUser", "firstPass");
        Account account2 = new Account("secondUser", "secondPass");

        List<Account> accounts = Arrays.asList(account, account2);
        createMultipleAccounts(accounts);

        mockMvc.perform(get("/api/account/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    private void createMultipleAccounts(List<Account> accounts) throws Exception {
        for(Account a : accounts){
            this.mockMvc.perform(post("/api/account/register")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(asJsonString(a)))
                    .andExpect(status().isOk());
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
