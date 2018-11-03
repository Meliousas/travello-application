package com.example.travello.repository;

import com.example.travello.entity.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository  extends CrudRepository<Account, Long> {
}
