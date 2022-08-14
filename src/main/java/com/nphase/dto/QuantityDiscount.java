package com.nphase.dto;

import java.math.BigDecimal;

public class QuantityDiscount extends Discount{

    private final int quantity;

    public QuantityDiscount(int quantity, BigDecimal value) {
        super(value);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}
