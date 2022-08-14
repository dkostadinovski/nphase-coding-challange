package com.nphase.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class ProductTest {

    @Test
    void createProduct_NameBlank_IllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Product(null, null, 0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Product("", null, 0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Product("  ", null, 0));
    }

    @Test
    void createProduct_PricePerUnitNull_IllegalStateException() {
        Assertions.assertThrows(IllegalStateException.class, () -> new Product("Tea", null, 0));
    }

    @Test
    void createProduct_PricePerUnitZeroOrNegative_IllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Product("Tea", BigDecimal.ZERO, 0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Product("Tea", BigDecimal.valueOf(-0.3), 0));
    }

    @Test
    void createProduct_QuantityZeroOrNegative_IllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Product("Tea", BigDecimal.ONE, 0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Product("Tea", BigDecimal.ONE, -1));
    }
}