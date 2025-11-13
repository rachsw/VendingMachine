package org.example;

import org.example.model.ChangeConfiguration;
import org.example.model.ManagedProduct;

import java.util.List;

public interface OperatorInterface {

    List<ManagedProduct> getItems();
    void removeItem(String productId);
    void updateItemPrice(String productId, int price);
    void updateItemStock(String productId, int stock);
    ManagedProduct createItem(ManagedProduct managedProduct);

    List<ChangeConfiguration> getTillContents();
    void updateCashStock(int coin, int stock);
}
