package org.example.database;

import org.example.model.Product;


import java.util.Map;

public class ProductDatabase {
    private final Map<String, Product> products;

    public ProductDatabase(Map<String, Product> products) {
        this.products = products;
    }

    void addProduct(Product product) {
        products.put(product.id(), product);
    }

    void remoteProduct(String productId) {
        products.remove(productId);
    }

    void updateProduct(Product product) {
        //replace the existing product with a entire new product
        products.put(product.id(), product);
    }
}
