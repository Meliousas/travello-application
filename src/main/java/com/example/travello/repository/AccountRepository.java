package com.example.travello.repository;

import com.example.travello.entity.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository  extends CrudRepository<Account, Long> {
    List<Account> findByUsername(String name);
}
