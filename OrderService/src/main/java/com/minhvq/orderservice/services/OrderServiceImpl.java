package com.minhvq.orderservice.services;

import com.minhvq.orderservice.DTOs.AccountDTO;
import com.minhvq.orderservice.DTOs.OrchidDTO;
import com.minhvq.orderservice.DTOs.OrderDTO;
import com.minhvq.orderservice.DTOs.OrderDetailDTO;
import com.minhvq.orderservice.entities.Order;
import com.minhvq.orderservice.entities.OrderDetail;
import com.minhvq.orderservice.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private OrchidService orchidClient;
    private AccountService accountClient;

    @Override
    public Order create(OrderDTO request) {
        AccountDTO accountDTO = accountClient.findById(request.getAccountId());
        Order order = new Order();
        order.setOrderDate(Timestamp.valueOf(LocalDateTime.now()));
        order.setOrderDetails(new ArrayList<>());
        order.setAccountId(accountDTO.getId());
        order.setStatus(request.getStatus());
        double total = 0;
        for (OrderDetailDTO detail : request.getDetails()) {
            OrchidDTO orchid = orchidClient.findById(detail.getOrchidId());
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setQuantity(detail.getQuantity());
            orderDetail.setPrice(detail.getPrice());
            total += orderDetail.getPrice() * orderDetail.getQuantity();
            order.getOrderDetails().add(orderDetail);
        }
        order.setTotalAmount(total);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(Integer id) {
        return orderRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        orderRepository.deleteById(id);
    }
}
