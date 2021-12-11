package com.yosep.spring.shop;

import lombok.Data;

@Data
public class Battery extends  Product{
    private boolean rechargeable;

    public Battery() {
        super();
    }

    public Battery(String name, double price) {
        super(name, price);
    }
}
