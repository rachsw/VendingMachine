package org.example.database;

import org.example.model.Product;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductDatabase {
    private final Map<String, Product> products;

    public ProductDatabase(Map<String, Product> products) {
        this.products = products;
    }

    void addProduct(Product product) {
        products.put(product.id(), product);
    }

    void removeProduct(String productId) {
        products.remove(productId);
    }

    void updateProduct(Product product) {
        //replace the existing product with an entire new product
        products.put(product.id(), product);
    }

    List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }
}
