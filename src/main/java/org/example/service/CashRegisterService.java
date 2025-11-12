package org.example.service;

import org.example.exceptions.BadInput;
import org.example.exceptions.SystemError;
import org.example.model.ChangeConfiguration;
import org.example.model.ChangeItem;

import java.util.*;

public class CashRegisterService {
    //Will order the keys
    private final Map<Integer, ChangeConfiguration> temporaryTill = new TreeMap<>(Comparator.reverseOrder());


    public CashRegisterService() {
        setupDefaultTill();
    }

    public CashRegisterService(List<ChangeConfiguration> changeConfigurations) {
        setupTill(changeConfigurations);
    }

    public List<ChangeConfiguration> getTillItems() {
        return new ArrayList<>(temporaryTill.values());
    }

    public ChangeConfiguration getCoinItem(int coin) {
        if (!temporaryTill.containsKey(coin)) {
            throw new BadInput("Item does not exist");
        }
        return temporaryTill.get(coin);
    }

    public void updateCoinStock(int coin, int stock) {
        ChangeConfiguration changeConfiguration = getCoinItem(coin);
        changeConfiguration.setStock(stock);
        temporaryTill.put(coin, changeConfiguration);
    }

    public void validateChange(List<ChangeItem> cashGiven, int price) {
        //need to check all the coins are ones we accept.
        boolean hasInvalid = cashGiven.stream()
                .anyMatch(coin -> !temporaryTill.containsKey(coin.getValue()));

        if (hasInvalid) {
            //return the coins here
            throw new BadInput("Some coins aren't accepted");
        }
    }

    // note we need to ensure there is enough change in the till before returning it
    public List<ChangeItem> getChange(List<ChangeItem> cashGiven, int price) throws Exception {

        for (ChangeItem cash : cashGiven) {
            price = price - (cash.getValue() * cash.getStock());
        }
        if(price == 0) {
            //correct amount of change
            return new ArrayList<>();
        }
        //overpaid
        if(price < 0) {
            return calculateChange(Math.abs(price));
        }
        else {
            throw new BadInput("Not enough cash provided ");
        }
    }

    private List<ChangeItem> calculateChange(int amountToReturn) {
        List<ChangeItem> change = new ArrayList<>();

        for (int coin : temporaryTill.keySet()) {
            int count = amountToReturn / coin;
            if (count > 0) {
                change.add(new ChangeItem(coin, count));
                amountToReturn -= coin * count;
            }
        }

        if (amountToReturn > 0) {
            throw new SystemError("Not enough coins");
        }
        return change;
    }

//   private List<ChangeItem> calculateCoins(BigDecimal amountLeft) {
//        List<ChangeItem> change = new ArrayList<>();
//        // lookup algorithm for returning change
//        //1.50
//        //We need the keyset to be ordered from highest to lowest
//        for (BigDecimal key : temporaryTill.keySet()) {
//           int stock = 0;
//
//            while (amountLeft.compareTo(key) >= 0) {
//                    amountLeft = amountLeft.subtract(key);
//                    stock++;
//                change.add(new ChangeItem(key, stock));
//            }
//            if (amountLeft.compareTo(BigDecimal.ZERO) == 0) {
//                break;
//            }
//        }
//        return change;
//    }

//    void setupTill() {
//        temporaryTill.put(new BigDecimal("2"), new ChangeConfiguration(new BigDecimal("2"), 5,5));
//        temporaryTill.put(new BigDecimal("1"), new ChangeConfiguration(new BigDecimal("1"), 5,  5));
//        temporaryTill.put(new BigDecimal("0.5"), new ChangeConfiguration(new BigDecimal("0.5"), 5,  5));
//        temporaryTill.put(new BigDecimal("0.2"), new ChangeConfiguration(new BigDecimal("0.2"), 5, 5));
//        temporaryTill.put(new BigDecimal("0.1"), new ChangeConfiguration(new BigDecimal("0.1"), 5,  5));
//    }

    private void setupDefaultTill() {
        temporaryTill.put(200, new ChangeConfiguration(200, 10));
        temporaryTill.put(100, new ChangeConfiguration(100, 10));
        temporaryTill.put(50, new ChangeConfiguration(100, 10));
        temporaryTill.put(20, new ChangeConfiguration(100, 10));
        temporaryTill.put(10, new ChangeConfiguration(10, 20));
    }

    void setupTill(List<ChangeConfiguration> configurations) {
        for (ChangeConfiguration configuration : configurations) {
            temporaryTill.put(configuration.getValue(), configuration);
        }
    }
}
