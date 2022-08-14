package com.nphase.entity;

import java.util.List;

public class ShoppingCart {

    // I'd use Set<Product> just not to have duplicate products
    private final List<Product> products;

    public ShoppingCart(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }
}
