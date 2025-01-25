package com.jaiswarup.stocky.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "stocks")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String stockName;

    @Column(nullable = false, unique = true)
    private String ticker;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private BigDecimal buyPrice;

    // Getters and Setters
}