package com.nphase.service;

import com.nphase.dto.QuantityDiscount;
import com.nphase.entity.Product;
import com.nphase.entity.ShoppingCart;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ShoppingCartService {

    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    private static final QuantityDiscount QUANTITY_DISCOUNT = new QuantityDiscount(3, BigDecimal.valueOf(0.9));

    public BigDecimal calculateTotalPrice(ShoppingCart shoppingCart) {

        List<Product.Category> discountedCategories = getDiscountedCategories(shoppingCart.getProducts());

        // todo change logic so you can apply different types of Discounts
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
        if (quantity > QUANTITY_DISCOUNT.getQuantity()) {
            return productPrice.multiply(QUANTITY_DISCOUNT.getValue());
        }
        return productPrice;
    }

    private List<Product.Category> getDiscountedCategories(List<Product> products) {
        return products.stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(x -> x.getValue() > 3L)
                .map(Map.Entry::getKey)
                .collect(Collectors.toUnmodifiableList());
    }
}
