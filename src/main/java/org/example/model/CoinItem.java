package org.example.model;

import java.util.Objects;

//simplify this.
//we can definitely just have a map of the change and the value.....
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

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CoinItem that)) return false;
        return value == that.value && stock == that.stock;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, stock);
    }
}
