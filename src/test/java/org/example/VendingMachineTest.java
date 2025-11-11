package org.example;

import org.example.model.CashConfiguration;
import org.example.model.Item;
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
        vm.createItem(new Item("A1", 2.0, 10));
        assertEquals(vm.viewProducts().size(), .50d,1);
    }

    @Test
    void canUpdateItemPriceInVendingMachine() {
        VendingMachine vm = new VendingMachine(5, Arrays.asList(
                new CashConfiguration(new BigDecimal("2"), 0, 10),
                new CashConfiguration(new BigDecimal("1"), 0, 10),
                new CashConfiguration(new BigDecimal("0.5"), 0, 10),
                new CashConfiguration(new BigDecimal("0.2"), 0, 10),
                new CashConfiguration(new BigDecimal("0.1"), 0, 10)
        ));
        vm.createItem(new Item("A1", 2.0, 10));
//        vm.updateProductStock()
        assertEquals(vm.viewProducts().size(), .50d,1);
    }
}