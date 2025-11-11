package org.example;

import org.example.database.ProductDatabase;
import org.example.model.CashConfiguration;
import org.example.model.Product;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachine implements ConsumerController, OperatorController {
// this sets up both interfaces
    final int productLimit;
    final List<CashConfiguration> acceptedCoins;
    final ProductDatabase productDb;

    public VendingMachine(int productLimit, List<CashConfiguration> acceptedCoins) {
        this.productLimit = productLimit;
        this.acceptedCoins = acceptedCoins;
        this.productDb = new ProductDatabase();
    }

    @Override
    public List<Product> ViewProducts() {
        return productDb.getAllProducts();
    }

    @Override
    public List<Product> ViewProduct(String productId) {
        return List.of();
    }

    @Override
    public List<CashConfiguration> PurchaseProduct(String productId, List<CashConfiguration> change) {
        return List.of();
    }

    @Override
    public List<Product> getProducts() {
        return List.of();
    }

    @Override
    public Product removeProduct(String productId) {
        return null;
    }

    @Override
    public Product updateProduct(Product product) {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        return productDb.addProduct(product);
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
