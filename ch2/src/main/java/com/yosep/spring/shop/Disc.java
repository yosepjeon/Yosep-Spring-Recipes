package com.yosep.spring.shop;

import lombok.Data;

@Data
public class Disc extends Product{
    private int capacity;

    public Disc() {
        super();
    }

    public Disc(String name, double price) {
        super(name, price);
    }
}
