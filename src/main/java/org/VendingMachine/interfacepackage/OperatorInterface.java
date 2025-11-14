package org.VendingMachine.interfacepackage;

import org.VendingMachine.model.CoinItem;
import org.VendingMachine.model.ManagedProduct;

import java.util.List;

public interface OperatorInterface {

    List<ManagedProduct> getItems();
    void removeItem(String productId);
    void updateItemPrice(String productId, int price);
    void updateItemStock(String productId, int stock);
    ManagedProduct createItem(ManagedProduct managedProduct);

    List<CoinItem> getTillContents();
    void updateCashStock(int coin, int stock);
}
