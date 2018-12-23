package com.example.travello.controller;

import com.example.travello.entity.Account;
import com.example.travello.repository.AccountRepository;
import com.example.travello.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    private static Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;


    @GetMapping("/principal")
    public Collection<? extends GrantedAuthority> user(Authentication principal){
        logger.info("Principal requested: {}", principal.getName());
        return principal.getAuthorities();
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
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
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteAccountById(@PathVariable Long id){
        logger.info("Deleting account with id: " + id);

        Optional<Account> account = accountService.getAccountById(id);

        if (!account.isPresent()) {
            logger.info("Unable to delete. Account with id: " + id + " not found");
            return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
        }

        accountService.deleteAccount(id);
        return new ResponseEntity<>(account.get(), HttpStatus.NO_CONTENT);
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<Account> createAccount(@RequestBody Account account){

        if(account.getUsername().trim().equals("") ||  account.getUsername() == null ||
                account.getPassword().trim().equals("") || account.getPassword() == null ||
                  account.getEmail().trim().equals("") || account.getEmail() == null ){
            logger.info("User registration for {} failed due to bad credentials", account);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(accountService.getAccountByUsername(account.getUsername()).isPresent()){
            logger.info("User registration failed due to username duplication");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Account createdAccount = accountService.createAccount(account);

        logger.info("Registering new user: {}", account);
        return new ResponseEntity<>(createdAccount, HttpStatus.OK);
    }

    @RequestMapping(value ="edit/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Account> editAccount(@PathVariable Long id, @RequestBody Account account){
        Optional<Account> accountDb = accountRepository.findById(id);

        if(!accountDb.isPresent()){
            logger.info("User with id: {} does not exist. Editing failed.", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Account editedAccount = accountService.updateAccount(id, account);

        logger.info("User with id: {} edited.", id);
        return new ResponseEntity<>(editedAccount, HttpStatus.OK);
    }

}
