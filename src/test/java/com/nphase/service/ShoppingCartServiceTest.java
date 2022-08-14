package com.nphase.service;


import com.nphase.entity.Product;
import com.nphase.entity.ShoppingCart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class ShoppingCartServiceTest {
    private final ShoppingCartService service = new ShoppingCartService();

    @Test
    public void calculatesPrice() {
        ShoppingCart cart = new ShoppingCart(Arrays.asList(
                new Product("Tea", BigDecimal.valueOf(5.0), 2),
                new Product("Coffee", BigDecimal.valueOf(6.5), 1)
        ));

        BigDecimal actual = service.calculateTotalPrice(cart);
        BigDecimal expected = BigDecimal.valueOf(16.5);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void calculateTotalPrice_TeaAndCoffee_Total12() {
        ShoppingCart cart = new ShoppingCart(List.of(
                new Product("Tea", BigDecimal.valueOf(5.0), 1),
                new Product("Coffee", BigDecimal.valueOf(3.5), 2)
        ));

        BigDecimal result = service.calculateTotalPrice(cart);

        Assertions.assertEquals(BigDecimal.valueOf(12.0), result);
    }

    @Test
    void calculateTotalPrice_2TeaAndCoffee_Total12() {
        ShoppingCart cart = new ShoppingCart(List.of(
                new Product("Tea", BigDecimal.valueOf(5.0), 1),
                new Product("Tea", BigDecimal.valueOf(5.0), 1),
                new Product("Coffee", BigDecimal.valueOf(3.5), 2)
        ));

        BigDecimal result = service.calculateTotalPrice(cart);

        Assertions.assertEquals(BigDecimal.valueOf(17.0), result);
    }

    @Test
    void calculateTotalPrice_TeaAndCoffee_Total11_3() {
        ShoppingCart cart = new ShoppingCart(List.of(
                new Product("Tea", BigDecimal.valueOf(5.123456789), 1),
                new Product("Coffee", BigDecimal.valueOf(3.123456789), 2)
        ));

        BigDecimal result = service.calculateTotalPrice(cart);

        Assertions.assertEquals(BigDecimal.valueOf(11.3), result);
    }

}