package com.nphase.service;

import com.nphase.entity.Product;
import com.nphase.entity.ShoppingCart;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class ShoppingCartService {

    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    public BigDecimal calculateTotalPrice(ShoppingCart shoppingCart) {
        return shoppingCart.getProducts()
                .stream()
                .map(this::calculateProductTotalPrice)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    private BigDecimal calculateProductTotalPrice(Product product) {
        if(Objects.isNull(product)){
            System.out.println("received null product; returning productPrice=0.0");
            return BigDecimal.ZERO;
        }

        final BigDecimal productPrice = product.getPricePerUnit().multiply(BigDecimal.valueOf(product.getQuantity()));
        return productPrice.setScale(1, ROUNDING_MODE);
    }
}
