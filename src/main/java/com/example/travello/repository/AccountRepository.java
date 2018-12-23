package com.example.travello.repository;

import com.example.travello.entity.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository  extends CrudRepository<Account, Long> {

    @Query( value = "select * from accounts a where a.username = ?1",
            nativeQuery = true)
    Account findByUsername(String name);
}
