package org.example.model;

import java.util.Objects;

// a user facing Coin item
public class CoinItem {
    int value;
    int stock;

    public CoinItem(int value, int stock) {
        this.value = value;
        this.stock = stock;
    }

    public int getValue() {
        return value;
    }

    public int getStock() {
        return stock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CoinItem)) return false;
        CoinItem coinItem = (CoinItem) o;
        return value == coinItem.value && stock == coinItem.stock;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, stock);
    }

    @Override
    public String toString() {
        return "CoinItem{" +
                "value=" + value +
                ", stock=" + stock +
                '}';
    }
}
