package org.example;

import org.example.model.CashConfiguration;
import org.example.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface OperatorController {

    List<Product> getProducts();
    Product removeProduct(String productId);
    Product updateProduct(Product product);
    Product createProduct(Product product);

    List<CashConfiguration> getTillContents();
    CashConfiguration removeCashItem(BigDecimal coin);
    CashConfiguration addCashItem(CashConfiguration coin);
    CashConfiguration updateCashItem(BigDecimal coin);
}
