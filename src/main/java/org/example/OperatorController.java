package org.example;

import org.example.model.Cash;
import org.example.model.Product;

import java.util.List;

public interface OperatorController {

    List<Product> getProducts();
    Product removeProduct();
    Product updateProduct(Product product);
    Product createProduct();

    List<Cash> getTillContents();
    Cash removeCashItem();
    Cash addCashItem();
    Cash updateCashItem();
}
