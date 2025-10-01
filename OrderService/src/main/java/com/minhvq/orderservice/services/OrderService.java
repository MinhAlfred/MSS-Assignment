package com.minhvq.orderservice.services;

import com.minhvq.orderservice.DTOs.OrderDTO;
import com.minhvq.orderservice.entities.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order create(OrderDTO request);

    List<Order> findAll();

    Optional<Order> findById(Integer id);

    void delete(Integer id);
}
