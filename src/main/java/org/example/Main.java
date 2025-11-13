package org.example;

import org.example.model.CoinItem;
import org.example.model.ManagedProduct;

import java.util.List;

public class Main {
    static void main() {

        VendingMachine vendingMachine = new VendingMachine(8,
                List.of(new CoinItem(100, 20),
                        new CoinItem(50, 20),
                        new CoinItem(10, 20)));

        vendingMachine.createItem(new ManagedProduct("A1 Coke", 140, 20));
        vendingMachine.createItem(new ManagedProduct("A2 Sprite", 130, 20));
        vendingMachine.createItem(new ManagedProduct("A3 Chocolate", 150, 20));
        vendingMachine.createItem(new ManagedProduct("A4 Coke Zero", 150, 20));
        vendingMachine.createItem(new ManagedProduct("A5 Coke Diet", 150, 20));
        vendingMachine.updateItemStock("A1 Coke", 5);
        vendingMachine.updateItemStock("A2 Sprite", 5);
        vendingMachine.updateItemStock("A3 Chocolate", 5);
        vendingMachine.updateItemStock("A4 Coke Zero", 5);
        vendingMachine.updateItemStock("A5 Coke Diet", 5);

        System.out.println("Items in vending machine: " + vendingMachine.viewProducts());

        var change = vendingMachine.purchaseProduct("A1 Coke", List.of(
                new CoinItem(100, 2)));
        System.out.println("Your change is:  " + change);
        System.out.println("Items in vending machine after purchase: " + vendingMachine.viewProducts());
        System.out.println("remaining change in machine: " + vendingMachine.getTillContents());
    }
}


//Items in vending machine: [Product{id='A2 Sprite', price=130, stock=5}, Product{id='A3 Chocolate', price=150, stock=5}, Product{id='A1 Coke', price=140, stock=5}, Product{id='A5 Coke Diet', price=150, stock=5}, Product{id='A4 Coke Zero', price=150, stock=5}]
//Your change is:  [CoinItem{value=50, stock=1}, CoinItem{value=10, stock=1}]
//Items in vending machine after purchase: [Product{id='A2 Sprite', price=130, stock=5}, Product{id='A3 Chocolate', price=150, stock=5}, Product{id='A1 Coke', price=140, stock=4}, Product{id='A5 Coke Diet', price=150, stock=5}, Product{id='A4 Coke Zero', price=150, stock=5}]
//remaining change in machine: [CoinItem{value=100, stock=22}, CoinItem{value=50, stock=19}, CoinItem{value=10, stock=19}]