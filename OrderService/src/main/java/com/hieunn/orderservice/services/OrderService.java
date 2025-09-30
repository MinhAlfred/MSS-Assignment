package com.hieunn.orderservice.services;

import com.hieunn.orderservice.DTOs.OrderDTO;
import com.hieunn.orderservice.entities.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order create(OrderDTO request);

    List<Order> findAll();

    Optional<Order> findById(Integer id);

    void delete(Integer id);
}
