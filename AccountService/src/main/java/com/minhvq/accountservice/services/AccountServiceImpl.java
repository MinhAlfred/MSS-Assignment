package com.minhvq.accountservice.services;

import com.minhvq.accountservice.entities.Account;
import com.minhvq.accountservice.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> findById(int id) {
        return accountRepository.findById(id);
    }

    @Override
    @Transactional
    public Account create(Account account) {
        return accountRepository.save(account);
    }

    @Override
    @Transactional
    public Account update(int id, Account account) {
        if (accountRepository.existsById(id)) {
            account.setId(id);
            return accountRepository.save(account);
        }
        return null;
    }

    @Override
    @Transactional
    public void delete(int id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    @Override
    public Account findByName(String name) {
        return accountRepository.findByName(name);
    }

    @Override
    public List<Account> findByRoleName(String roleName) {
        return accountRepository.findByRole_Name(roleName);
    }

    @Override
    public List<Account> findByRoleId(Integer roleId) {
        return accountRepository.findByRole_Id(roleId);
    }

    @Override
    public List<Account> searchByEmail(String email) {
        return accountRepository.findByEmailContaining(email);
    }

    @Override
    public boolean existsByEmail(String email) {
        return accountRepository.existsByEmail(email);
    }
}
