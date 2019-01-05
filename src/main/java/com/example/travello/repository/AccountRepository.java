package com.example.travello.repository;

import com.example.travello.entity.Account;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface AccountRepository  extends CrudRepository<Account, Long> {

    @Query( value = "select * from accounts a where a.username = ?1",
            nativeQuery = true)
    @Transactional
    Account findByUsername(String name);

    @Query( value = "select * from accounts a where a.email = ?1",
            nativeQuery = true)
    Account findByEmail(String s);

    @Transactional
    @Modifying
    @Query(value = "update accounts set is_active = ?2 where id = ?1",
            nativeQuery = true)
    void updateStatus(long id, boolean status);
}
