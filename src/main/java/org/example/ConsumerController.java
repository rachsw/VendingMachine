package org.example;

import org.example.model.CashConfiguration;
import org.example.model.Product;

import java.util.List;

public interface ConsumerController {

    List<Product> ViewProducts();
    //get the products price

    List<Product> ViewProduct(String productId);

    List<CashConfiguration> PurchaseProduct(String productId, List<CashConfiguration> change);

}
