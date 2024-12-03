package com.example.tdd.domain.product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;
    private int quantity;
    private double price;
    private double discountRate;

    @Builder
    public Product(String name, String category, int quantity, double price, double discountRate) {
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
        this.discountRate = discountRate;
    }

}