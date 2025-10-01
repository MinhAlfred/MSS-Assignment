package com.minhvq.accountservice.configs;

import com.minhvq.accountservice.entities.Account;
import com.minhvq.accountservice.entities.Role;
import com.minhvq.accountservice.repositories.AccountRepository;
import com.minhvq.accountservice.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public void run(String... args) {
        if (roleRepository.count() > 0 || accountRepository.count() > 0) {
            System.out.println("Data already exists, skipping initialization");
            return;
        }

        //Create roles
        Role adminRole = new Role();
        adminRole.setName("ADMIN");
        roleRepository.save(adminRole);

        Role userRole = new Role();
        userRole.setName("USER");
        roleRepository.save(userRole);

        //Create accounts
        Account admin = new Account();
        admin.setName("admin");
        admin.setEmail("admin@example.com");
        admin.setPassword("admin123");
        admin.setRole(adminRole);
        accountRepository.save(admin);

        Account user = new Account();
        user.setName("user");
        user.setEmail("user@example.com");
        user.setPassword("user123");
        user.setRole(userRole);
        accountRepository.save(user);

        System.out.println("Data has been initialized successfully");
    }
}
