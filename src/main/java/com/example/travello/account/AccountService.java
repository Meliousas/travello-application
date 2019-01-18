package com.example.travello.account;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
        return account;
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    public Account updateAccount(Long id, Account account) {
        account.setId(id);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }

    public void changeStatus(long id, boolean status) {
       accountRepository.updateStatus(id, status);
    }
}
