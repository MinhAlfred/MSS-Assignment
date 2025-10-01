package com.minhvq.orderservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "order_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer orchidId;

    private Integer quantity;

    private Double price;
}
