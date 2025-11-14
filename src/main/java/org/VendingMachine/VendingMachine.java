package org.VendingMachine;

import org.VendingMachine.database.ItemDatabase;
import org.VendingMachine.interfacepackage.ConsumerInterface;
import org.VendingMachine.interfacepackage.OperatorInterface;
import org.VendingMachine.model.CoinItem;
import org.VendingMachine.model.ManagedProduct;
import org.VendingMachine.model.Product;
import org.VendingMachine.service.CashRegisterService;

import java.util.List;

public class VendingMachine implements ConsumerInterface, OperatorInterface {
    final int itemsLimit;
    final List<CoinItem> acceptedCoins;
    final private ItemDatabase itemsDb;
    final private CashRegisterService cashRegisterService;

    public VendingMachine(int itemsLimit, List<CoinItem> acceptedCoins) {
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

    public List<CoinItem> purchaseProduct(String productId, List<CoinItem> change) {
        var item = itemsDb.getItemsById(productId);
        if (item.getStock() <= 0) {
            throw new IllegalStateException("Not enough stock of this product");
        }
        itemsDb.updateItemStock(productId, item.getStock() - 1);
        //could add a Log here if stock is getting low.

        return cashRegisterService.processCoinTransaction(change, item.getPrice());
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

    public List<CoinItem> getTillContents() {
        return cashRegisterService.getTillItems();
    }

    public void updateCashStock(int coin, int stock) {
        cashRegisterService.updateCoinStock(coin, stock);
    }
}
