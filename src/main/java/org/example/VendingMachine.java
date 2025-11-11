package org.example;

import java.util.List;

public class VendingMachine {
// this sets up both interfaces
    final int products;
    final List<Double> acceptedCoins;

    public VendingMachine(int products, List<Double> acceptedCoins) {
        this.products = products;
        this.acceptedCoins = acceptedCoins;
    }
}
