package com.yosep.spring.shop;

import lombok.Data;

@Data
public abstract class Product {
    private String name;
    private double price;

    public Product() {}

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
