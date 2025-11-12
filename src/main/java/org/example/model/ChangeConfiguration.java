package org.example.model;

import java.math.BigDecimal;

public class ChangeConfiguration extends ChangeItem {

    int limit;

    //don't use bigDecimal, use int and store as 10,20,50,100,200
    public ChangeConfiguration(int value, int stock, int limit) {
        super(value, stock);
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
