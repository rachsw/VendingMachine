package org.example.database;

import org.example.model.Item;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemDatabase {
    private final Map<String, Item> items;

    public ItemDatabase() {
        this.items = new HashMap<>();
    }

    public Item addItem(Item item) {
        return this.items.put(item.getId(), item);
    }

    public void removeItem(String itemId) {
        items.remove(itemId);
    }

    public void updateItem(Item item) {
        //replace the existing product with an entire new product
        this.items.put(item.getId(), item);
    }

    public List<Item> getAllItems() {
        return new ArrayList<>(items.values());
    }
}
