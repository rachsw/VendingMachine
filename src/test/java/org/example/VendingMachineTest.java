package org.example;

import org.example.model.ChangeConfiguration;
import org.example.model.ProductItem;
import org.example.model.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineTest {

    private final VendingMachine vendingMachine = new VendingMachine(5, Arrays.asList(
            new ChangeConfiguration(new BigDecimal("2"), 0, 10),
            new ChangeConfiguration(new BigDecimal("1"), 0, 10),
            new ChangeConfiguration(new BigDecimal("0.5"), 0, 10),
            new ChangeConfiguration(new BigDecimal("0.2"), 0, 10),
            new ChangeConfiguration(new BigDecimal("0.1"), 0, 10)
    ));

    @Test
    void canAddNewProductToVendingMachine() {
        VendingMachine vm = new VendingMachine(5, Arrays.asList(
                new ChangeConfiguration(new BigDecimal("2"), 0, 10),
                new ChangeConfiguration(new BigDecimal("1"), 0, 10),
                new ChangeConfiguration(new BigDecimal("0.5"), 0, 10),
                new ChangeConfiguration(new BigDecimal("0.2"), 0, 10),
                new ChangeConfiguration(new BigDecimal("0.1"), 0, 10)
        ));
        vm.createItem(new ProductItem("A1", 200, 10));
        assertEquals(.50d, vm.viewProducts().size(),1);
    }

    @Test
    void canUpdateItemPriceInVendingMachine() {
        VendingMachine vm = new VendingMachine(5, Arrays.asList(
                new ChangeConfiguration(new BigDecimal("2"), 0, 10),
                new ChangeConfiguration(new BigDecimal("1"), 0, 10),
                new ChangeConfiguration(new BigDecimal("0.5"), 0, 10),
                new ChangeConfiguration(new BigDecimal("0.2"), 0, 10),
                new ChangeConfiguration(new BigDecimal("0.1"), 0, 10)
        ));
        vm.createItem(new ProductItem("A1", 200, 10));
        //Todo fix me
//        vm.updateProductStock()
        assertEquals(.50d, vm.viewProducts().size(),1);
    }

    @Test
    void canViewProductById(){
        var item1 = vendingMachine.createItem(new ProductItem("A1", 100, 10));
        vendingMachine.createItem(new ProductItem("A2", 200, 10));

        var expectedProduct = new Product(item1.getId(), item1.getPrice(), 0);

        var actual = vendingMachine.viewProduct(item1.getId());

        assertEquals(expectedProduct, actual);
    }

    @Test
    void canViewProductList(){
        var item1 = vendingMachine.createItem(new ProductItem("A1", 100, 10));
        var item2 = vendingMachine.createItem(new ProductItem("A2", 200, 10));

        var expectedProduct1 = new Product(item1.getId(), item1.getPrice(), 0);
        var expectedProduct2 = new Product(item2.getId(), item2.getPrice(), 0);

        var actual = vendingMachine.viewProducts();

        assertEquals(Boolean.TRUE, actual.contains(expectedProduct1));
        assertEquals(Boolean.TRUE, actual.contains(expectedProduct2));
    }

    @Test
    void canUpdateItemStock() {
        var item1 = vendingMachine.createItem(new ProductItem("A1", 100, 10));
        var expectedStock = 5;
        vendingMachine.updateItemStock(item1.getId(), expectedStock);
        var actual = vendingMachine.viewProduct(item1.getId()).getStock();

        assertEquals(expectedStock, actual);
    }

    @Test
    void canPurchaseProduct(){
        var item1 = vendingMachine.createItem(new ProductItem("A1", 100, 10));
        vendingMachine.updateItemStock(item1.getId(), 5);
        vendingMachine.purchaseProduct(item1.getId(), new ArrayList<>());

        var actual = vendingMachine.viewProduct(item1.getId());

        assertEquals(4, actual.getStock());
    }
}