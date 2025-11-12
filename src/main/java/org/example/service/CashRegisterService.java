package org.example.service;

import org.example.exceptions.BadInput;
import org.example.model.ChangeConfiguration;
import org.example.model.ChangeItem;

import java.math.BigDecimal;
import java.util.*;

public class CashRegisterService {
    //Will order the keys
    Map<Integer, ChangeConfiguration> temporaryTill = new TreeMap<>(Comparator.reverseOrder());


    public CashRegisterService() {
//        setupTill();
    }

    public CashRegisterService(List<ChangeConfiguration> changeConfigurations) {
        setupTill(changeConfigurations);
    }

    // note we need to ensure there is enough change in the till before returning it
//    public List<ChangeItem> getChange(List<ChangeItem> cashGiven, BigDecimal price) throws Exception {

//        for (ChangeItem cash : cashGiven) {
//            price = price.subtract(cash.getValue().multiply(BigDecimal.valueOf(cash.getStock())));
//        }
//        if(price.compareTo(BigDecimal.ZERO) == 0) {
//            return new ArrayList<>();
//        }
//        //overpaid
//        if (price.compareTo(BigDecimal.ZERO) < 0) {
//            return calculateCoins(price.abs());
//        }
//        else {
//            throw new BadInput("Not enough cash provided ");
//        }
//    }
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

    void setupTill(List<ChangeConfiguration> configurations) {
        for (ChangeConfiguration configuration : configurations) {
            temporaryTill.put(configuration.getValue(), configuration);
        }
    }
}
