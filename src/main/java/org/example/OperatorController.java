package org.example;

import org.example.model.CashConfiguration;
import org.example.model.Item;

import java.math.BigDecimal;
import java.util.List;

public interface OperatorController {

    List<Item> getItems();
    Item removeItem(String productId);
    Item updateItemPrice(String productId, double price);
    Item updateItemStock(String productId, int stock);
    Item createItem(Item item);

    List<CashConfiguration> getTillContents();
    CashConfiguration removeCashItem(BigDecimal coin);
    CashConfiguration addCashItem(CashConfiguration coin);
    CashConfiguration updateCashItem(BigDecimal coin);
}
