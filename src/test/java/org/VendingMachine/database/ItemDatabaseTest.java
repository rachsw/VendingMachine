package org.VendingMachine.database;

import org.VendingMachine.model.ManagedProduct;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;

class ItemDatabaseTest {

    private ItemDatabase itemDatabase = new ItemDatabase(5);

    @Test
    void canCreateItemInDatabase() {
        var expected = new ManagedProduct("A1", 200, 10);

        var actual = itemDatabase.createItem(expected);

        assertEquals(expected, actual);
        assertEquals(Boolean.TRUE, itemDatabase.getAllItems().contains(expected));
    }

    @Test
    void canGetItemInDatabase() {
        var expected = new ManagedProduct("A1", 200, 10);

        itemDatabase.createItem(expected);
        var actual = itemDatabase.getItemsById(expected.getId());

        assertEquals(expected, actual);
        assertEquals(Boolean.TRUE, itemDatabase.getAllItems().contains(expected));
    }

    @Test
    void canGetAllItemsInDatabase() {
        var item1 = new ManagedProduct("A1", 100, 10);
        var item2 = new ManagedProduct("A2", 200, 10);
        var item3 = new ManagedProduct("A3", 250, 10);

        itemDatabase.createItem(item1);
        itemDatabase.createItem(item2);
        itemDatabase.createItem(item3);

        var actual = itemDatabase.getAllItems();

        assertEquals(3, actual.size());
        assertEquals(Boolean.TRUE, actual.contains(item1));
        assertEquals(Boolean.TRUE, actual.contains(item2));
        assertEquals(Boolean.TRUE, actual.contains(item3));
    }

    @Test
    void canDeleteItemInDatabase() {
        var item1 = new ManagedProduct("A1", 100, 10);
        var item2 = new ManagedProduct("A2", 200, 10);
        var item3 = new ManagedProduct("A3", 250, 10);

        itemDatabase.createItem(item1);
        itemDatabase.createItem(item2);
        itemDatabase.createItem(item3);

        itemDatabase.removeItem(item1.getId());

        assertEquals(2, itemDatabase.getAllItems().size());
        assertEquals(Boolean.FALSE, itemDatabase.getAllItems().contains(item1));
    }

    @Test
    void cannotCreateItemThatAlreadyExists() {
        var item1 = new ManagedProduct("A1", 100, 10);
        itemDatabase.createItem(item1);
        var error = Assertions.assertThrows(IllegalArgumentException.class, () -> itemDatabase.createItem(item1));

        assertEquals("Item already exists", error.getMessage());
    }

    @Test
    void cannotCreateItemIfVendingMachineFull() {
        var item1 = new ManagedProduct("A1", 100, 10);
        var item2 = new ManagedProduct("A2", 100, 10);
        var item3 = new ManagedProduct("A3", 100, 10);
        var item4 = new ManagedProduct("A4", 100, 10);
        var item5 = new ManagedProduct("A5", 100, 10);
        var item6 = new ManagedProduct("A6", 100, 10);
        itemDatabase.createItem(item1);
        itemDatabase.createItem(item2);
        itemDatabase.createItem(item3);
        itemDatabase.createItem(item4);
        itemDatabase.createItem(item5);
        var error = Assertions.assertThrows(IllegalStateException.class, () -> itemDatabase.createItem(item6));

        assertEquals("Product limit reached please remove products before adding new ones", error.getMessage());
    }

    @Test
    void cannotGetItemThatDoesNotExists() {
        var item1 = new ManagedProduct("A1", 100, 10);
        var error = Assertions.assertThrows(IllegalArgumentException.class, () -> itemDatabase.getItemsById(item1.getId()));

        assertEquals("Item does not exist", error.getMessage());
    }

    @Test
    void canUpdateStockOfItem() {
        var item1 = new ManagedProduct("A1", 100, 10);
        var newStock = 5;
        itemDatabase.createItem(item1);

        itemDatabase.updateItemStock(item1.getId(), newStock);

        assertEquals(newStock, itemDatabase.getItemsById(item1.getId()).getStock());
    }

    @Test
    void canUpdatePriceOfItem() {
        var item1 = new ManagedProduct("A1", 100, 10);
        var newPrice = 200;
        itemDatabase.createItem(item1);

        itemDatabase.updateItemPrice(item1.getId(), newPrice);

        assertEquals(newPrice, itemDatabase.getItemsById(item1.getId()).getPrice());
    }

}