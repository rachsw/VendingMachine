package org.example;

import org.example.database.ItemDatabase;
import org.example.model.CashConfiguration;
import org.example.model.Item;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachine implements ConsumerController, OperatorController {
// this sets up both interfaces
    final int itemsLimit;
    final List<CashConfiguration> acceptedCoins;
    final ItemDatabase itemsDb;

    public VendingMachine(int itemsLimit, List<CashConfiguration> acceptedCoins) {
        this.itemsLimit = itemsLimit;
        this.acceptedCoins = acceptedCoins;
        this.itemsDb = new ItemDatabase();
    }

    @Override
    public List<Item> viewProducts() {
        return itemsDb.getAllItems();
    }

    @Override
    public List<Item> viewProduct(String productId) {
        return List.of();
    }

    @Override
    public List<CashConfiguration> purchaseProduct(String productId, List<CashConfiguration> change) {
        return List.of();
    }

    @Override
    public List<Item> getItems() {
        return List.of();
    }

    @Override
    public Item removeItem(String productId) {
        return null;
    }

    @Override
    public Item updateItemPrice(String productId, double price) {
        return null;
    }

    @Override
    public Item updateItemStock(String productId, int stock) {
        return null;
    }

    @Override
    public Item createItem(Item item) {
        return itemsDb.addItem(item);
    }

    @Override
    public List<CashConfiguration> getTillContents() {
        return List.of();
    }

    @Override
    public CashConfiguration removeCashItem(BigDecimal coin) {
        return null;
    }

    @Override
    public CashConfiguration addCashItem(CashConfiguration coin) {
        return null;
    }

    @Override
    public CashConfiguration updateCashItem(BigDecimal coin) {
        return null;
    }
}
