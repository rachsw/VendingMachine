package org.example.service;

import org.example.model.CoinItem;

import java.util.*;

public class CashRegisterService {
    //Will order the keys
    private final Map<Integer, Integer> changeTill = new TreeMap<>(Comparator.reverseOrder());
    private final Map<Integer, Integer> temporaryChangeStore = new HashMap<>();

    public CashRegisterService() {
        setupDefaultTill();
    }

    public CashRegisterService(List<CoinItem> changeConfigurations) {
        setupTill(changeConfigurations);
    }

    //helper method
    public CoinItem coinItemMapper(Integer value, Integer stock) {
        return new CoinItem(value, stock);
    }

    public List<CoinItem> getTillItems() {
        var mappedValues = changeTill.keySet().stream().map(key -> coinItemMapper(key, changeTill.get(key))).toList();
        return new ArrayList<>(mappedValues);
    }

    public CoinItem getCoinItem(int coin) {
        if (!changeTill.containsKey(coin)) {
            throw new IllegalArgumentException("Item does not exist");
        }
        return coinItemMapper(coin, changeTill.get(coin));
    }

    public void updateCoinStock(int coin, int stock) {
        changeTill.put(coin, stock);
    }

    //Todo Steps to implement
    public List<CoinItem> purchaseProductWithCoins(List<CoinItem> cashGiven, int price) {
        // the product service provides the cash service with the price and cash given
        //we need to first validate the coins are ones we can accept
        validateChange(cashGiven);
        //then we temporarily store the coins somewhere
        for (CoinItem cash : cashGiven) {
            temporaryChangeStore.put(cash.getValue(), cash.getStock());
        }
        // if there is enough change exactly we return no change
        var change = getChange(cashGiven, price);
        // if there is not enough change we return the change and cancel the operation
        // if there is change leftover we
        //loop through and see how much change we need to provide and make checks to see if the change has stock in both the till and temporary holder
        //if we dont have enough change throw an error and stop the purchase
        // if we do have enough change then we provide that back to the user
        //then we put the temporary change in the till
        // then we take away the change we want to give to the user from the till
        for (CoinItem coinItem : change) {
            updateCoinStock(coinItem.getValue(), getCoinItem(coinItem.getValue()).getStock() - coinItem.getStock());
        }
        return change;
    }

    //helper method
    public void validateChange(List<CoinItem> cashGiven) {
        //need to check all the coins are ones we accept.
        boolean hasInvalid = cashGiven.stream()
                .anyMatch(coin -> !changeTill.containsKey(coin.getValue()));

        if (hasInvalid) {
            //return the coins here
            throw new IllegalArgumentException("Some coins aren't accepted");
        }
    }

    // note we need to ensure there is enough change in the till before returning it
    public List<CoinItem> getChange(List<CoinItem> cashGiven, int price) {
        int totalGiven = cashGiven.stream()
                .mapToInt(c -> c.getValue() * c.getStock())
                .sum();

        if (totalGiven < price) {
            throw new IllegalArgumentException("Not enough cash provided");
        }

        if (totalGiven == price) {
            for (Integer changeItem : temporaryChangeStore.keySet()) {
                //update coin stock to the existing till stock
                updateCoinStock(changeItem, changeTill.get(changeItem) + temporaryChangeStore.get(changeItem));
            }
            //no change needed to return
            return List.of();
        }

        int leftOver = totalGiven - price;
        return calculateChange(leftOver);
    }

    boolean validateStockAvailable(int key, int stock) {
        var tempStock = temporaryChangeStore.get(key) == null ? 0 : temporaryChangeStore.get(key);
        var tillStock = changeTill.get(key);
        return stock < tempStock + tillStock;
    }

    private List<CoinItem> calculateChange(int amountToReturn) {
        List<CoinItem> change = new ArrayList<>();
        for (int key : changeTill.keySet()) {
            int stock = 0;
            while (amountToReturn >= key) {
                if(validateStockAvailable(key, stock)) {
                    amountToReturn = amountToReturn - key;
                    stock++;
                } else {
                    break;
                }
            }
            if (stock > 0) {
                change.add(new CoinItem(key, stock));
            }
        }

        if (amountToReturn > 0) {
            throw new IllegalStateException("Not enough coins");
        }
        return change;
    }


//    void setupTill() {
//        temporaryTill.put(new BigDecimal("2"), new ChangeConfiguration(new BigDecimal("2"), 5,5));
//        temporaryTill.put(new BigDecimal("1"), new ChangeConfiguration(new BigDecimal("1"), 5,  5));
//        temporaryTill.put(new BigDecimal("0.5"), new ChangeConfiguration(new BigDecimal("0.5"), 5,  5));
//        temporaryTill.put(new BigDecimal("0.2"), new ChangeConfiguration(new BigDecimal("0.2"), 5, 5));
//        temporaryTill.put(new BigDecimal("0.1"), new ChangeConfiguration(new BigDecimal("0.1"), 5,  5));
//    }

    private void setupDefaultTill() {
        changeTill.put(200, 10);
        changeTill.put(100, 10);
        changeTill.put(50, 10);
        changeTill.put(20, 10);
        changeTill.put(10, 10);
    }

    void setupTill(List<CoinItem> configurations) {
        for (CoinItem configuration : configurations) {
            changeTill.put(configuration.getValue(), configuration.getStock());
        }
    }
}
