package com.minhvq.orderservice.controllers;

import com.minhvq.orderservice.DTOs.ApiResponse;
import com.minhvq.orderservice.DTOs.OrderDTO;
import com.minhvq.orderservice.entities.Order;
import com.minhvq.orderservice.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
@CrossOrigin("*")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse<Order>> create(@RequestBody OrderDTO request) {
        try {
            Order order = orderService.create(request);
            ApiResponse<Order> response = ApiResponse.success(order, "Order created successfully", 201);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            ApiResponse<Order> response = ApiResponse.error("Failed to create order: " + e.getMessage(), 400);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Order>>> findAll() {
        try {
            List<Order> orders = orderService.findAll();
            ApiResponse<List<Order>> response = ApiResponse.success(orders, "Orders retrieved successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse<List<Order>> response = ApiResponse.error("Failed to retrieve orders: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Order>> findById(@PathVariable Integer id) {
        try {
            Optional<Order> orderOptional = orderService.findById(id);
            if (orderOptional.isPresent()) {
                ApiResponse<Order> response = ApiResponse.success(orderOptional.get(), "Order found successfully");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                ApiResponse<Order> response = ApiResponse.error("Order not found", 404);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            ApiResponse<Order> response = ApiResponse.error("Failed to retrieve order: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Order>> update(@PathVariable Integer id, @RequestBody OrderDTO request) {
        try {
            Optional<Order> existingOrder = orderService.findById(id);
            if (existingOrder.isPresent()) {
                // Set the ID to the request to update existing order
                Order updatedOrder = orderService.create(request);
                updatedOrder.setId(id); // Ensure the ID is maintained
                ApiResponse<Order> response = ApiResponse.success(updatedOrder, "Order updated successfully");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                ApiResponse<Order> response = ApiResponse.error("Order not found", 404);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            ApiResponse<Order> response = ApiResponse.error("Failed to update order: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Integer id) {
        try {
            Optional<Order> existingOrder = orderService.findById(id);
            if (existingOrder.isPresent()) {
                orderService.delete(id);
                ApiResponse<Void> response = ApiResponse.success("Order deleted successfully");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                ApiResponse<Void> response = ApiResponse.error("Order not found", 404);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            ApiResponse<Void> response = ApiResponse.error("Failed to delete order: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
