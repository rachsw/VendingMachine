package org.example.model;

import java.math.BigDecimal;
import java.util.Objects;

public class ChangeItem {
    BigDecimal value;
    int stock;

    public ChangeItem(BigDecimal value, int stock) {
        this.value = value;
        this.stock = stock;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
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
