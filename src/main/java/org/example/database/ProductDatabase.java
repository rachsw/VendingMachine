package org.example.database;

import org.example.model.Product;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDatabase {
    private final Map<String, Product> products;

    public ProductDatabase() {
        this.products = new HashMap<>();
    }

    public Product addProduct(Product product) {
        return products.put(product.getId(), product);
    }

    public void removeProduct(String productId) {
        products.remove(productId);
    }

    public void updateProduct(Product product) {
        //replace the existing product with an entire new product
        products.put(product.getId(), product);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }
}
