package com.minhvq.orderservice.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailDTO {
    private Integer id;
    private Integer orchidId;
    private Integer quantity;
    private Double price;
}
