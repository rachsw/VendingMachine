package org.example;

import org.example.database.ItemDatabase;
import org.example.exceptions.BadInput;
import org.example.exceptions.SystemError;
import org.example.model.ChangeConfiguration;
import org.example.model.ManagedProduct;
import org.example.model.Product;
import org.example.service.CashRegisterService;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachine implements ConsumerController, OperatorController {
// this sets up both interfaces
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
        return itemsDb.getAllItems().stream().map(this::toProduct).toList();
    }

    public Product viewProduct(String productId) {
        return toProduct(itemsDb.getItemsById(productId));
    }

    private Product toProduct(ManagedProduct managedProduct) {
        return new Product(managedProduct.getId(), managedProduct.getPrice(), managedProduct.getStock());
    }

    public List<ChangeConfiguration> purchaseProduct(String productId, List<ChangeConfiguration> change) {
        var itemStock = itemsDb.getItemsById(productId).getStock();
        if (itemStock <= 0) {
            throw new SystemError("Not enough stock of this product");
        }
        itemsDb.updateItemStock(productId, itemStock - 1);
        //Log here if stock is getting low
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
        //technically we are making an inefficient call to the DB to firstly check limit AND then if
        //the item exists. we should probably only make a db object call once to prevent this.
        if (stock > itemsDb.getItemsById(productId).getLimit()){
            throw new BadInput("Over the stock limit");
        }
        itemsDb.updateItemStock(productId, stock);
    }

    public ManagedProduct createItem(ManagedProduct managedProduct) {
        return itemsDb.createItem(managedProduct);
    }

    public List<ChangeConfiguration> getTillContents() {
        return List.of();
    }

    public ChangeConfiguration removeCashItem(BigDecimal coin) {
        return null;
    }

    public ChangeConfiguration addCashItem(ChangeConfiguration coin) {
        return null;
    }

    public ChangeConfiguration updateCashItem(BigDecimal coin) {
        return null;
    }
}
