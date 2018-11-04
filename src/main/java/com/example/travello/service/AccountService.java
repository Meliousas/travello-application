package com.example.travello.service;

import com.example.travello.entity.Account;
import com.example.travello.repository.AccountRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public List<Account> getAccounts(){
        return Lists.newArrayList(accountRepository.findAll());
    }

    public Optional<Account> getAccountById(Long id){ return accountRepository.findById(id);}

    public List<Account> getAccountsByUsername(String name) {
        return accountRepository.findByUsername(name);
    }

    public Account createAccount(Account account){
        accountRepository.save(account);
        return account;
    }
}
