package org.example.model;

import java.math.BigDecimal;
import java.util.Objects;

public class ChangeItem {
    int value;
    int stock;

    public ChangeItem(int value, int stock) {
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
        if (o == null || getClass() != o.getClass()) return false;
        ChangeItem that = (ChangeItem) o;
        return stock == that.stock && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, stock);
    }
}
