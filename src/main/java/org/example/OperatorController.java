package org.example;

import org.example.model.ChangeConfiguration;
import org.example.model.ProductItem;

import java.math.BigDecimal;
import java.util.List;

public interface OperatorController {

    List<ProductItem> getItems();
    void removeItem(String productId);
    void updateItemPrice(String productId, int price);
    void updateItemStock(String productId, int stock);
    ProductItem createItem(ProductItem productItem);

    List<ChangeConfiguration> getTillContents();
    ChangeConfiguration removeCashItem(BigDecimal coin);
    ChangeConfiguration addCashItem(ChangeConfiguration coin);
    ChangeConfiguration updateCashItem(BigDecimal coin);
}
