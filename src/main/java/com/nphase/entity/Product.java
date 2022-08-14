package com.nphase.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private final String name;
    private final BigDecimal pricePerUnit;
    private final int quantity;
    private final Category category;


    public Product(String name, BigDecimal pricePerUnit, int quantity, Category category) {
        this.name = name;
        this.pricePerUnit = pricePerUnit;
        this.quantity = quantity;
        this.category = category;

        validateName(name);
        validatePricePerUnit(pricePerUnit);
        validateQuantity(quantity);
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public int getQuantity() {
        return quantity;
    }

    public Category getCategory() {
        return category;
    }

    private void validateName(String name) {
        if (Objects.isNull(name) || name.isBlank()) {
            throw new IllegalArgumentException("'name' should not be blank;");
        }
    }

    private void validatePricePerUnit(BigDecimal pricePerUnit) {
        if (Objects.isNull(pricePerUnit)) {
            throw new IllegalStateException("'pricePerUnit' should be given; received 'null'");
        }
        if (pricePerUnit.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("'pricePerUnit' should be greater then 0.0; received=" + pricePerUnit);
        }
    }

    private void validateQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity should be greater then 0; received quantity=" + quantity);
        }
    }

    public enum Category {
        DRINKS,
        FOOD
    }
}
