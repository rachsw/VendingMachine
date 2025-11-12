package org.example.database;

import org.example.exceptions.BadInput;
import org.example.model.ProductItem;
import org.example.model.Product;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemDatabase {
    //temporary map of String, where String is the ItemId
    private final Map<String, ProductItem> items;

    private final int limit;

    public ItemDatabase(int limit) {
        this.items = new HashMap<>();
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }

    public ProductItem createItem(ProductItem productItem) {
        //logic will throw if the item already exists here
        if(items.containsKey(productItem.getId())) {
            throw new BadInput("Item already exists");
        }
        items.put(productItem.getId(), productItem);
        return items.get(productItem.getId());
    }

    public void removeItem(String itemId) {
        items.remove(itemId);
    }

    public void updateItemPrice(String id, int newPrice) {
        //rules here: Item needs to exist, id cannot be updated, limit cannot be updated.
        //but stock and price can be updated if necessary.
        ProductItem productItem = getItemsById(id);
        productItem.setPrice(newPrice);
        items.put(id, productItem);
    }

    //I want to be able to update the stock without worrying about updating other parts of the item by accident
    public void updateItemStock(String id, int stockAmount) {
        ProductItem productItem = getItemsById(id);
        productItem.setStock(stockAmount);
        items.put(id, productItem);
    }

    public List<ProductItem> getAllItems() {
        return new ArrayList<>(items.values());
    }

    public ProductItem getItemsById(String id) {
        if(!items.containsKey(id)) {
            throw new BadInput("Item does not exist");
        }
        return items.get(id);
    }
}
