package org.example.database;

import org.example.model.ManagedProduct;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemDatabase {
    //temporary map of String, where String is the ItemId
    private final Map<String, ManagedProduct> items;

    private final Integer limit;

    public ItemDatabase(int limit) {
        this.items = new HashMap<>();
        this.limit = limit;
    }

    private boolean hasReachedProductLimit() {
        return limit.equals(items.size());
    }

    public ManagedProduct createItem(ManagedProduct managedProduct) {
        //logic will throw if the item already exists here
        if(items.containsKey(managedProduct.getId())) {
            throw new IllegalArgumentException("Item already exists");
        }
        if(hasReachedProductLimit()) {
            throw new IllegalStateException("Product limit reached please remove products before adding new ones");
        }
        items.put(managedProduct.getId(), managedProduct);
        return items.get(managedProduct.getId());
    }

    public void removeItem(String itemId) {
        items.remove(itemId);
    }

    public void updateItemPrice(String id, int newPrice) {
        //rules here: Item needs to exist, id cannot be updated, limit cannot be updated.
        //but stock and price can be updated if necessary.
        ManagedProduct managedProduct = getItemsById(id);
        managedProduct.setPrice(newPrice);
        items.put(id, managedProduct);
    }

    //I want to be able to update the stock without worrying about updating other parts of the item by accident
    public void updateItemStock(String id, int stockAmount) {
        ManagedProduct managedProduct = getItemsById(id);

        if (stockAmount > managedProduct.getLimit()) {
            throw new IllegalStateException("Over the stock limit");
        }
        managedProduct.setStock(stockAmount);
        items.put(id, managedProduct);
    }

    public List<ManagedProduct> getAllItems() {
        return new ArrayList<>(items.values());
    }

    public ManagedProduct getItemsById(String id) {
        if(!items.containsKey(id)) {
            throw new IllegalArgumentException("Item does not exist");
        }
        return items.get(id);
    }
}
