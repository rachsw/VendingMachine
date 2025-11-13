package org.example;

import org.example.database.ItemDatabase;
import org.example.model.ChangeConfiguration;
import org.example.model.ManagedProduct;
import org.example.model.Product;
import org.example.service.CashRegisterService;

import java.util.List;

public class VendingMachine implements ConsumerInterface, OperatorInterface {
    final int itemsLimit;
    final List<ChangeConfiguration> acceptedCoins;
    final ItemDatabase itemsDb;
    final CashRegisterService cashRegisterService;

    public VendingMachine(int itemsLimit, List<ChangeConfiguration> acceptedCoins) {
        this.itemsLimit = itemsLimit;
        this.acceptedCoins = acceptedCoins;
        this.itemsDb = new ItemDatabase(itemsLimit);
        this.cashRegisterService = new CashRegisterService(acceptedCoins);
    }

    public List<Product> viewProducts() {
        //we might not want to expose what the operator can see to the consumer object.
        // this is an implementation to return product info to the consumer
        return itemsDb.getAllItems().stream().map(this::toProduct).toList();
    }

    public Product viewProduct(String productId) {
        return toProduct(itemsDb.getItemsById(productId));
    }

    //essentially this is mapping the product to a customer facing product
    private Product toProduct(ManagedProduct managedProduct) {
        return new Product(managedProduct.getId(), managedProduct.getPrice(), managedProduct.getStock());
    }

    public List<ChangeConfiguration> purchaseProduct(String productId, List<ChangeConfiguration> change) {
        var itemStock = itemsDb.getItemsById(productId).getStock();
        if (itemStock <= 0) {
            throw new IllegalStateException("Not enough stock of this product");
        }
        itemsDb.updateItemStock(productId, itemStock - 1);
        //could add a Log here if stock is getting low.
        //check stock
        //decrease stock
        //todo return cash
        return List.of();
    }

    public List<ManagedProduct> getItems() {
        return itemsDb.getAllItems();
    }

    public void removeItem(String productId) {
        itemsDb.removeItem(productId);
    }

    public void updateItemPrice(String productId, int price) {
        itemsDb.updateItemPrice(productId, price);
    }

    public void updateItemStock(String productId, int stock) {
        itemsDb.updateItemStock(productId, stock);
    }

    public ManagedProduct createItem(ManagedProduct managedProduct) {
        return itemsDb.createItem(managedProduct);
    }

    public List<ChangeConfiguration> getTillContents() {
        return cashRegisterService.getTillItems();
    }

    public void updateCashStock(int coin, int stock) {
        cashRegisterService.updateCoinStock(coin, stock);
    }
}
