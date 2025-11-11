package org.example.model;

import java.math.BigDecimal;
import java.util.Date;

public class CashConfiguration {
    BigDecimal value;
    int stock;
    int limit;

    public CashConfiguration(BigDecimal value, int stock, int limit) {
        this.value = value;
        this.stock = stock;
        this.limit = limit;
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

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
