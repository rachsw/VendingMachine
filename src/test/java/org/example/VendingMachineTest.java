package org.example;

import org.example.model.CashConfiguration;
import org.example.model.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineTest {

    @Test
    void canAddNewProductToVendingMachine() {
        VendingMachine vm = new VendingMachine(5, Arrays.asList(
                new CashConfiguration(new BigDecimal("2"), 0, 10),
                new CashConfiguration(new BigDecimal("1"), 0, 10),
                new CashConfiguration(new BigDecimal("0.5"), 0, 10),
                new CashConfiguration(new BigDecimal("0.2"), 0, 10),
                new CashConfiguration(new BigDecimal("0.1"), 0, 10)
        ));
        new Product("A1", 2.50d, 10, 10);
        vm.createProduct()
    }
}