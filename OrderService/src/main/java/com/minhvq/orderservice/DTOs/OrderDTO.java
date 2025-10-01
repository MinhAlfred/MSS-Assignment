package com.minhvq.orderservice.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {
    private Integer id;
    private Timestamp orderDate;
    private Double totalAmount;
    private Integer accountId;
    private String status;
    private List<OrderDetailDTO> details;
}
