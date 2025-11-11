package org.example;

import org.example.model.CashConfiguration;
import org.example.model.Item;

import java.util.List;

public interface ConsumerController {

    List<Item> viewProducts();
    //get the products price

    List<Item> viewProduct(String productId);

    List<CashConfiguration> purchaseProduct(String productId, List<CashConfiguration> change);

}
