package org.example;

import org.example.model.Cash;
import org.example.model.Product;

import java.util.List;

public interface ConsumerController {

    List<Product> ViewProducts() ;
    //get the products price
    List<Product> ViewProduct(String productId);

    List<Cash> PurchaseProducts(String productId, List<Cash> change) ;

}
