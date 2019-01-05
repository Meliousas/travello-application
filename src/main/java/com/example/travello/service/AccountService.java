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

    public Optional<Account> getAccountByUsername(String name) {
        return Optional.ofNullable(accountRepository.findByUsername(name));
    }

    public Optional<Account> getAccountByEmail(String name) {
        return Optional.ofNullable(accountRepository.findByEmail(name));
    }

    public Account createAccount(Account account){
        accountRepository.save(account);
        return account;
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    public Account updateAccount(Long id, Account account) {
        account.setId(id);
        return accountRepository.save(account);
    }

    public void changeStatus(long id, boolean status) {
       accountRepository.updateStatus(id, status);
    }
}
