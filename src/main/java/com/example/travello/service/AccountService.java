package com.example.travello.service;

import com.example.travello.entity.Account;
import com.example.travello.repository.AccountRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public List<Account> getAccounts(){
        return Lists.newArrayList(accountRepository.findAll());
    }

    public Account createAccount(Account account){
        accountRepository.save(account);
        return account;
    }
}
