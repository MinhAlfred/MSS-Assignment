package com.minhvq.accountservice.services;

import com.minhvq.accountservice.entities.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    List<Account> findAll();

    Optional<Account> findById(int id);

    Account create(Account account);

    Account update(int id, Account account);

    void delete(int id);

    Account findByEmail(String email);

    Account findByName(String name);

    List<Account> findByRoleName(String roleName);

    List<Account> findByRoleId(Integer roleId);

    List<Account> searchByEmail(String email);

    boolean existsByEmail(String email);
}
