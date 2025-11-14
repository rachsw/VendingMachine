package org.VendingMachine.model;

import java.util.Objects;

public class Product {

    public Product(String id, int price, int stock) {
        this.id = id;
        this.price = price;
        this.stock = stock;
    }

    // this will be A1, A2 etc places in the vending machine
    String id;
    //the Price, will be x100
    int price;

    // how many of this item we have stocked the machine with
    int stock;

    public String getId() {
        return id;
    }

    //check int is ok for this?
    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return price == product.price && stock == product.stock && Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, stock);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}
