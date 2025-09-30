package com.hieunn.orderservice.services;

import com.hieunn.orderservice.DTOs.AccountDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "account-service", url = "http://localhost:8081")
public interface AccountService {
    @GetMapping("/api/accounts/{id}")
    AccountDTO findById(@PathVariable Integer id);
}
