package com.nphase.service;

import com.nphase.entity.Product;
import com.nphase.entity.ShoppingCart;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class ShoppingCartService {

    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    private static final int DISCOUNT_QUANTITY = 3;
    private static final BigDecimal DISCOUNTED_PRICE = BigDecimal.valueOf(0.9);

    public BigDecimal calculateTotalPrice(ShoppingCart shoppingCart) {
        return shoppingCart.getProducts()
                .stream()
                .map(this::calculateProductTotalPrice)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    private BigDecimal calculateProductTotalPrice(Product product) {
        final BigDecimal productPrice = calculateProductPrice(product);
        final BigDecimal discountedPrice = calculateDiscount(productPrice, product.getQuantity());

        return discountedPrice.setScale(1, ROUNDING_MODE);
    }

    private BigDecimal calculateProductPrice(Product product) {
        if (Objects.isNull(product)) {
            System.out.println("received null product; returning productPrice=0.0");
            return BigDecimal.ZERO;
        }

        return product.getPricePerUnit().multiply(BigDecimal.valueOf(product.getQuantity()));
    }

    private BigDecimal calculateDiscount(BigDecimal productPrice, int quantity) {
        if (quantity > DISCOUNT_QUANTITY) {
            return productPrice.multiply(DISCOUNTED_PRICE);
        }
        return productPrice;
    }
}
