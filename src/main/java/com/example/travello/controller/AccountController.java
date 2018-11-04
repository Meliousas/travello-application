package com.example.travello.controller;

import com.example.travello.entity.Account;
import com.example.travello.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Account> getAccountById(@PathVariable Long id){
        Optional<Account> account = accountService.getAccountById(id);

        if(!account.isPresent()){
            logger.info("Account with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            logger.info("Account with id {} found", id);
            return new ResponseEntity<>(account.get(), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<Account> createAccount(@RequestBody Account account){

        if(account.getUsername().trim().equals("") ||  account.getUsername() == null ||
                account.getPassword().trim().equals("") || account.getPassword() == null ){
            logger.info("User registration for {} failed due to bad credentials", account);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Account createdAccount = accountService.createAccount(account);

        logger.info("Registering new user: {}", account);
        return new ResponseEntity<>(createdAccount, HttpStatus.OK);
    }

}
