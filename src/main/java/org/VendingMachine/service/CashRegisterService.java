package org.VendingMachine.service;

import org.VendingMachine.model.CoinItem;

import java.util.*;

public class CashRegisterService {
    //Will order the keys by higher coins first(default order is smallest to largest)
    //this is so that we can have an algorithm that selects highers coin value first as change
    private final Map<Integer, Integer> changeTill = new TreeMap<>(Comparator.reverseOrder());
    public CashRegisterService() {
        setupDefaultTill();
    }

    public CashRegisterService(List<CoinItem> changeConfigurations) {
        setupTill(changeConfigurations);
    }

    //This is for testing purposes
    private void setupDefaultTill() {
        changeTill.put(200, 10);
        changeTill.put(100, 10);
        changeTill.put(50, 10);
        changeTill.put(20, 10);
        changeTill.put(10, 10);
    }

    private void setupTill(List<CoinItem> configurations) {
        for (CoinItem configuration : configurations) {
            changeTill.put(configuration.getValue(), configuration.getStock());
        }
    }

    //helper method
    public CoinItem coinItemMapper(Integer value, Integer stock) {
        return new CoinItem(value, stock);
    }

    public List<CoinItem> getTillItems() {
        var mappedValues = changeTill.keySet().stream().map(key -> coinItemMapper(key, changeTill.get(key))).toList();
        return new ArrayList<>(mappedValues);
    }

    // Leaving this here in case we want to use it for the future
    public CoinItem getCoinItem(int coin) {
        if (!changeTill.containsKey(coin)) {
            throw new IllegalArgumentException("Item does not exist");
        }
        return coinItemMapper(coin, changeTill.get(coin));
    }

    public void updateCoinStock(int coin, int stock) {
        changeTill.put(coin, stock);
    }

    //synchronized should allow for this method  makes this class and method lock until this method has finished
    public synchronized List<CoinItem> processCoinTransaction(List<CoinItem> coinsProvided, int price) {

        validateCoinsAreCorrectType(coinsProvided); //validate coinsProvided types: done

        var totalReceivedAmount = calculateTotalReceived(coinsProvided);//check if coinsProvided are enough

        if (totalReceivedAmount < price) {
            throw new IllegalArgumentException("Not enough cash provided");//not enough, dont add to till //throw error //do we catch error in vending machine and return change? possibly
        }

        if (totalReceivedAmount == price) { //check if coinsProvided add up to price.

            coinsProvided.forEach(coin ->
                    changeTill.merge(coin.getValue(), coin.getStock(), Integer::sum)
            );
            return List.of();//return emptyList
        }
        var changeRequired = totalReceivedAmount - price; //coinsProvided add up to price with extra,

        var temporaryTill = getCoinsAvailable(coinsProvided); //check how much change we need by making temporary till

        List<CoinItem> coinsToReturn =  calculateChangeToReturn(temporaryTill, changeRequired); //not enough stock, throw error, return change

        updateTillAfterChange(temporaryTill, coinsToReturn); //enough stock, //add money to till
        return coinsToReturn; // return correct amount of change

    }

    private void updateTillAfterChange(Map<Integer, Integer> temporaryTill, List<CoinItem> coinsToReturn) {
        coinsToReturn.forEach(coin ->
                temporaryTill.computeIfPresent(coin.getValue(),
                        (k, v) -> v - coin.getStock())
        );
        // Commit simulated till state to real till
        //this is not a very safe option as we are essentially just replacing all items in the till with the ones we simulated which would not work in a shared database and if this was not locked.
        temporaryTill.forEach((coin, stock) -> changeTill.put(coin, stock));
    }

    //here making a temporary till which is the user provided change plus existing till money to be able to get an idea of stock.
    private Map<Integer, Integer> getCoinsAvailable(List<CoinItem> coins) {
        Map<Integer, Integer> tempTill = new HashMap<>(changeTill);
        coins.forEach(coinItem ->
                tempTill.merge(coinItem.getValue(), coinItem.getStock(), Integer::sum));
        return tempTill;
    }

    public List<CoinItem> calculateChangeToReturn(Map<Integer, Integer> coinsAvailable, int changeAmountRequired) {
        List<CoinItem> change = new ArrayList<>();
        var coinKeysAvailable = coinsAvailable.keySet().stream().sorted(Comparator.reverseOrder()).toList();
        for (int key : coinKeysAvailable) {
            int coinCounter = 0;
            while (changeAmountRequired >= key && coinCounter < coinsAvailable.get(key)) {
                changeAmountRequired -= key;
                coinCounter++;
            }

            if (coinCounter > 0) {
                change.add(new CoinItem(key, coinCounter));
            }
        }
        if (changeAmountRequired > 0) {
            throw new IllegalStateException("Not enough coins");
        }
        return change;
    }

    //helper Methods below
    public int calculateTotalReceived(List<CoinItem> coins) {
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
}
