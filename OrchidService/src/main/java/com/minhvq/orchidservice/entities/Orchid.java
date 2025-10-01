package com.minhvq.orchidservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orchids")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Orchid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Builder.Default
    private Boolean isNatural = false;

    private String description;

    private Integer price;

    private String url;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
