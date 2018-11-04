package com.example.travello.controller;

import com.example.travello.entity.Account;
import com.example.travello.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    static Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<Account>> getAllAccounts(){
        List<Account> accounts = accountService.getAccounts();

        logger.info("Requesting all accounts list: {} objects", accounts.size());
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<Account> createAccount(@RequestBody Account account){

        if(account.getUsername().trim().equals("") ||  account.getUsername() == null ||
                account.getPassword().trim().equals("") || account.getPassword() == null ){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Account createdAccount = accountService.createAccount(account);

        logger.info("Registering new user: {}", account);
        return new ResponseEntity<>(createdAccount, HttpStatus.OK);
    }

}
