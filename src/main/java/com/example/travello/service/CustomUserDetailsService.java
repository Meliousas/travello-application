package com.example.travello.service;

import com.example.travello.entity.Account;
import com.example.travello.entity.CustomUserDetails;
import com.example.travello.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Account user = accountRepository.findByEmail(s);
        if (user == null) {
            throw new UsernameNotFoundException(s);
        }
        return new CustomUserDetails(user);
    }
}
