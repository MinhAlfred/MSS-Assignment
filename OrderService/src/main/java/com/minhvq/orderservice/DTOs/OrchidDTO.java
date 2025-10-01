package com.minhvq.orderservice.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrchidDTO {
    private Integer id;
    private String name;
    private Boolean isNatural;
    private String description;
    private Integer price;
    private String url;
}
