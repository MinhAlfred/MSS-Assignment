package com.minhvq.accountservice.controllers;

import com.minhvq.accountservice.DTOs.ApiResponse;
import com.minhvq.accountservice.entities.Account;
import com.minhvq.accountservice.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
@CrossOrigin("*")
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<ApiResponse<Account>> create(@RequestBody Account request) {
        try {
            Account account = accountService.create(request);
            ApiResponse<Account> response = ApiResponse.success(account, "Account created successfully", 201);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            ApiResponse<Account> response = ApiResponse.error("Failed to create account: " + e.getMessage(), 400);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Account>>> findAll() {
        try {
            List<Account> accounts = accountService.findAll();
            ApiResponse<List<Account>> response = ApiResponse.success(accounts, "Accounts retrieved successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse<List<Account>> response = ApiResponse.error("Failed to retrieve accounts: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Account>> findById(@PathVariable Integer id) {
        try {
            Optional<Account> accountOptional = accountService.findById(id);
            if (accountOptional.isPresent()) {
                ApiResponse<Account> response = ApiResponse.success(accountOptional.get(), "Account found successfully");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                ApiResponse<Account> response = ApiResponse.error("Account not found", 404);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            ApiResponse<Account> response = ApiResponse.error("Failed to retrieve account: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Account>> update(@PathVariable Integer id, @RequestBody Account request) {
        try {
            Optional<Account> existingAccount = accountService.findById(id);
            if (existingAccount.isPresent()) {
                request.setId(id); // Ensure the ID is maintained
                Account updatedAccount = accountService.create(request);
                ApiResponse<Account> response = ApiResponse.success(updatedAccount, "Account updated successfully");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                ApiResponse<Account> response = ApiResponse.error("Account not found", 404);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            ApiResponse<Account> response = ApiResponse.error("Failed to update account: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Integer id) {
        try {
            Optional<Account> existingAccount = accountService.findById(id);
            if (existingAccount.isPresent()) {
                accountService.delete(id);
                ApiResponse<Void> response = ApiResponse.success("Account deleted successfully");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                ApiResponse<Void> response = ApiResponse.error("Account not found", 404);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            ApiResponse<Void> response = ApiResponse.error("Failed to delete account: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody Account loginRequest) {
        try {
            // Simple authentication - find account by username and check password
            List<Account> accounts = accountService.findAll();
            Account foundAccount = accounts.stream()
                .filter(acc -> acc.getName().equals(loginRequest.getName()) &&
                              acc.getPassword().equals(loginRequest.getPassword()))
                .findFirst()
                .orElse(null);

            if (foundAccount != null) {
                String token = "token_" + foundAccount.getId() + "_" + System.currentTimeMillis();
                ApiResponse<String> response = ApiResponse.success(token, "Login successful");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                ApiResponse<String> response = ApiResponse.error("Invalid credentials", 401);
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            ApiResponse<String> response = ApiResponse.error("Login failed: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
