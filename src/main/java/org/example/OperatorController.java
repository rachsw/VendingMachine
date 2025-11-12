package org.example;

import org.example.model.ChangeConfiguration;
import org.example.model.ManagedProduct;

import java.math.BigDecimal;
import java.util.List;

public interface OperatorController {

    List<ManagedProduct> getItems();
    void removeItem(String productId);
    void updateItemPrice(String productId, int price);
    void updateItemStock(String productId, int stock);
    ManagedProduct createItem(ManagedProduct managedProduct);

    List<ChangeConfiguration> getTillContents();
    ChangeConfiguration removeCashItem(BigDecimal coin);
    ChangeConfiguration addCashItem(ChangeConfiguration coin);
    ChangeConfiguration updateCashItem(BigDecimal coin);
}
