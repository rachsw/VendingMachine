package org.example;

import org.example.model.ChangeConfiguration;
import org.example.model.Product;

import java.util.List;

public interface ConsumerInterface {

    //Consumer only needs to realistically see a product that has an ID and a price
    List<Product> viewProducts();
    //get the products price
    Product viewProduct(String productId);

    List<ChangeConfiguration> purchaseProduct(String productId, List<ChangeConfiguration> change);

}
