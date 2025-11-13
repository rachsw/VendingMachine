package org.example;


import org.example.model.CoinItem;
import org.example.model.ManagedProduct;
import org.example.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineTest {

    private final VendingMachine vendingMachine = new VendingMachine(5, List.of(
            new CoinItem(200,  10),
            new CoinItem(100,  10),
            new CoinItem(50,  10),
            new CoinItem(20,  10),
            new CoinItem(10, 10)
    ));

    @Test
    void canAddNewProductToVendingMachine() {
        VendingMachine vm = new VendingMachine(5, List.of(
                new CoinItem(200,  10),
                new CoinItem(100,  10),
                new CoinItem(50,  10),
                new CoinItem(20,  10),
                new CoinItem(10,  10)
        ));
        vm.createItem(new ManagedProduct("A1", 200, 10));
        assertEquals(.50d, vm.viewProducts().size(),1);
    }

    @Test
    void canUpdateItemPriceInVendingMachine() {
        var expected = 5;
        var product = new ManagedProduct("A1", 200, 10);
        VendingMachine vendingMachine = new VendingMachine(5, List.of(
                new CoinItem(200,  10),
                new CoinItem(100,  10),
                new CoinItem(50,  10),
                new CoinItem(20,  10),
                new CoinItem(10,  10)
        ));
        vendingMachine.createItem(product);
        vendingMachine.updateItemStock(product.getId(), expected);
        assertEquals(.50d, vendingMachine.viewProducts().size(),1);
        assertEquals(expected, vendingMachine.viewProduct(product.getId()).getStock());
    }

    @Test
    void canViewProductById(){
        var item1 = vendingMachine.createItem(new ManagedProduct("A1", 100, 10));
        vendingMachine.createItem(new ManagedProduct("A2", 200, 10));

        var expectedProduct = new Product(item1.getId(), item1.getPrice(), 0);

        var actual = vendingMachine.viewProduct(item1.getId());

        assertEquals(expectedProduct, actual);
    }

    @Test
    void canViewProductList(){
        var item1 = vendingMachine.createItem(new ManagedProduct("A1", 100, 10));
        var item2 = vendingMachine.createItem(new ManagedProduct("A2", 200, 10));

        var expectedProduct1 = new Product(item1.getId(), item1.getPrice(), 0);
        var expectedProduct2 = new Product(item2.getId(), item2.getPrice(), 0);

        var actual = vendingMachine.viewProducts();

        assertEquals(Boolean.TRUE, actual.contains(expectedProduct1));
        assertEquals(Boolean.TRUE, actual.contains(expectedProduct2));
    }

    @Test
    void canUpdateItemStock() {
        var item1 = vendingMachine.createItem(new ManagedProduct("A1", 100, 10));
        var expectedStock = 5;
        vendingMachine.updateItemStock(item1.getId(), expectedStock);
        var actual = vendingMachine.viewProduct(item1.getId()).getStock();

        assertEquals(expectedStock, actual);
    }

    @Test
    void canFailToUpdateItemStockIfItsFull() {
        var item1 = vendingMachine.createItem(new ManagedProduct("A1", 100, 10));
        var expectedStock = 11;

        var actual = Assertions.assertThrows(IllegalStateException.class, () -> vendingMachine.updateItemStock(item1.getId(), expectedStock));

        assertEquals("Over the stock limit", actual.getMessage());
    }

    @Test
    void canPurchaseProduct(){
        var item1 = vendingMachine.createItem(new ManagedProduct("A1", 100, 10));
        vendingMachine.updateItemStock(item1.getId(), 5);
        vendingMachine.purchaseProduct(item1.getId(), List.of(new CoinItem(100, 1)));

        var actual = vendingMachine.viewProduct(item1.getId());

        assertEquals(4, actual.getStock());
        var tillContents = vendingMachine.getTillContents();
        assertEquals(Boolean.TRUE, tillContents.contains(new CoinItem(100, 11)));
    }

    @Test
    void canPurchaseProductAndValidateChange(){
        var item1 = vendingMachine.createItem(new ManagedProduct("A1", 450, 10));
        vendingMachine.updateItemStock(item1.getId(), 5);
        var change = vendingMachine.purchaseProduct(item1.getId(), List.of(
                new CoinItem(100, 2),
                new CoinItem(200, 2),
                new CoinItem(10, 6)));

        var actual = vendingMachine.viewProduct(item1.getId());

        assertEquals(4, actual.getStock());
        var tillContents = vendingMachine.getTillContents();
        //validate till contents updates correctly
        assertEquals(Boolean.TRUE, tillContents.contains(new CoinItem(200, 11)));
        assertEquals(Boolean.TRUE, tillContents.contains(new CoinItem(100, 12)));
        assertEquals(Boolean.TRUE, tillContents.contains(new CoinItem(10, 15)));
        //validate change returned
        assertEquals(List.of(new CoinItem(200, 1), new CoinItem(10, 1)), change);
    }
}