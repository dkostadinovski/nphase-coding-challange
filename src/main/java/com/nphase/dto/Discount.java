package com.nphase.dto;

import java.math.BigDecimal;

public class Discount {

    private final BigDecimal value;

    public Discount(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }
}
