package org.example.service;

import org.example.model.CoinItem;

import java.util.*;
import java.util.List;

public class ReplacementTillServiceClass {
    private final Map<Integer, Integer> changeTill = new TreeMap<>(Comparator.reverseOrder());

    public List<CoinItem> processCoinTransaction(List<CoinItem> coinsProvided, int price) {
        //validate coinsProvided types: done
        validateCoinsAreCorrectType(coinsProvided);

        //check if coinsProvided are enough
        var totalReceivedAmount = calculateTotalReceived(coinsProvided, price);
        //not enough, dont add to till
        //throw error
        //do we catch error in vending machine and return change? possibly
        if (totalReceivedAmount < price) {
            throw new IllegalArgumentException("Not enough cash provided");
        }

        //check if coinsProvided add up to price.
        if (totalReceivedAmount == price) {
            //add to till
            //return emptyList
            coinsProvided.forEach(
                    coin -> updateCoinStock(
                            coin.getValue(), changeTill.get(coin.getValue()) + coin.getStock())
            );
            return List.of();
        }

        //coinsProvided add up to price with extra,
        var changeRequired = totalReceivedAmount - price;
        //check how much change we need
        //check stock of how much change we need
        //make temporary till
        var temporaryTill = getCoinsAvailable(coinsProvided);

        //not enough stock, throw error, return change
        //enough stock,
        var coinsToReturn =  calculateChangeToGiveBasedOnSimulatedTill(temporaryTill, changeRequired);
        //add money to till
        updateTillAfterChange(temporaryTill, coinsToReturn);
        return coinsToReturn;
        // return correct amount of change
    }

    private void updateTillAfterChange(Map<Integer, Integer> temporaryTill, List<CoinItem> coinsToReturn) {
        coinsToReturn.forEach(coin ->
                temporaryTill.computeIfPresent(coin.getValue(),
                        (k, v) -> v - coin.getStock())
        );

        // Commit simulated till state to real till
        changeTill.putAll(temporaryTill);
    }


    //here making a temporary till which is the user provided change plus existing till money to be able to get an idea of stock.
    private Map<Integer, Integer> getCoinsAvailable(List<CoinItem> coins) {
        Map<Integer, Integer> tempTill = new HashMap<>(changeTill);
        coins.forEach(coinItem -> tempTill.put(coinItem.getValue(), tempTill.get(coinItem.getValue()) + coinItem.getStock()));
        return tempTill;
    }

    private List<CoinItem> calculateChangeToGiveBasedOnSimulatedTill(Map<Integer, Integer> coinsAvailable, int changeAmountRequired) {
        List<CoinItem> change = new ArrayList<>();
        var coinsTypeAvailable = coinsAvailable.keySet().stream().sorted(Comparator.reverseOrder()).toList();
        for (int key : coinsTypeAvailable) {
            int stock = 0;
            while (changeAmountRequired >= key) {
                if (stock <= coinsAvailable.get(key)) {
                    changeAmountRequired = changeAmountRequired - key;
                    stock ++;
                } else {
                    break;
                }
            }
            if (stock > 0) {
                change.add(new CoinItem(key, stock));
            }
        }
        if (changeAmountRequired > 0) {
            throw new IllegalStateException("Not enough coins");
        }
        return change;
    }

    //helper Methods below
    public int calculateTotalReceived(List<CoinItem> coins, int price) {
        return coins.stream().mapToInt(coin -> coin.getValue() * coin.getStock())
                .sum();
    }

    public void validateCoinsAreCorrectType(List<CoinItem> cashGiven) {
        //need to check all the coins are ones we accept.
        boolean hasInvalid = cashGiven.stream()
                .anyMatch(coin -> !changeTill.containsKey(coin.getValue()));

        if (hasInvalid) {
            //return the coins here
            throw new IllegalArgumentException("Some coins aren't accepted");
        }
    }

    public void updateCoinStock(int coin, int stock) {
        changeTill.put(coin, stock);
    }
}
