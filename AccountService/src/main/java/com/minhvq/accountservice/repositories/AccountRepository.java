package com.minhvq.accountservice.repositories;

import com.minhvq.accountservice.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByEmail(String email);

    Account findByName(String name);

    List<Account> findByRole_Id(int id);

    List<Account> findByRole_Name(String name);

    List<Account> findByEmailContaining(String email);

    boolean existsByEmail(String email);

    List<Account> findByNameContaining(String name);
}
