package org.example;

import org.example.database.ItemDatabase;
import org.example.exceptions.SystemError;
import org.example.model.ChangeConfiguration;
import org.example.model.ProductItem;
import org.example.model.Product;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachine implements ConsumerController, OperatorController {
// this sets up both interfaces
    final int itemsLimit;
    final List<ChangeConfiguration> acceptedCoins;
    final ItemDatabase itemsDb;

    public VendingMachine(int itemsLimit, List<ChangeConfiguration> acceptedCoins) {
        this.itemsLimit = itemsLimit;
        this.acceptedCoins = acceptedCoins;
        this.itemsDb = new ItemDatabase(itemsLimit);
    }

    public List<Product> viewProducts() {
        //we might not want to expose what the operator can see to the consumer object.
        return itemsDb.getAllItems().stream().map(this::toProduct).toList();
    }

    public Product viewProduct(String productId) {
        return toProduct(itemsDb.getItemsById(productId));
    }

    private Product toProduct(ProductItem productItem) {
        return new Product(productItem.getId(), productItem.getPrice(), productItem.getStock());
    }

    public List<ChangeConfiguration> purchaseProduct(String productId, List<ChangeConfiguration> change) {
        var itemStock = itemsDb.getItemsById(productId).getStock();
        if(!( itemStock > 0)) {
            throw new SystemError("Not enough stock of this product");
        }
        itemsDb.updateItemStock(productId, itemStock - 1);
        //check stock
        //decrease stock
        //return cash
        return List.of();
    }

    public List<ProductItem> getItems() {
        return itemsDb.getAllItems();
    }

    public void removeItem(String productId) {
        itemsDb.removeItem(productId);
    }

    public void updateItemPrice(String productId, int price) {
        itemsDb.updateItemPrice(productId, price);
    }

    public void updateItemStock(String productId, int stock) {
        itemsDb.updateItemStock(productId, 5);
    }

    public ProductItem createItem(ProductItem productItem) {
        return itemsDb.createItem(productItem);
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
