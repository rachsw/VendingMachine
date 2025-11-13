package org.example.interfacepackage;

import org.example.model.CoinItem;
import org.example.model.Product;

import java.util.List;

public interface ConsumerInterface {

    //Consumer only needs to realistically see a product that has an ID and a price
    List<Product> viewProducts();
    //get the products price
    Product viewProduct(String productId);

    List<CoinItem> purchaseProduct(String productId, List<CoinItem> change);

}
