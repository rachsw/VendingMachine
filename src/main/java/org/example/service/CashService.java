package org.example.service;

import org.example.model.Cash;
import org.example.model.ChangeItem;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class CashService {
    HashMap<BigDecimal, Cash> temporaryTill = new HashMap<>();

    public CashService() {
        setupTill();
    }

    // note we need to ensure there is enough change in the till before returning it
    List<ChangeItem> getChange(List<ChangeItem> cashGiven, BigDecimal price) throws Exception {

        for (ChangeItem cash : cashGiven) {
            price = price.subtract(cash.value().multiply(BigDecimal.valueOf(cash.stock())));
        }
        if(price.compareTo(BigDecimal.ZERO) == 0) {
            return new ArrayList<>();
        }
        //overpaid
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            return calculateCoins(price.multiply(BigDecimal.valueOf(-1)));
        }
        else {
            throw new Exception("Not enough cash provided ");
        }
    }
   public List<ChangeItem> calculateCoins(BigDecimal amountLeft) {
        List<ChangeItem> change = new ArrayList<>();
        // lookup algorithm for returning change
        //1.50
        //We need the keyset to be ordered from highest to lowest
       List<BigDecimal> sortedKeys = temporaryTill.keySet().stream().sorted(Comparator.reverseOrder()).toList();
        for (BigDecimal key : sortedKeys) {
           int stock = 0;

            while (amountLeft.compareTo(key) >= 0) {
                    amountLeft = amountLeft.subtract(key);
                    stock++;
                change.add(new ChangeItem(key, stock));
            }
        }
        return change;
    }

    void setupTill() {
        temporaryTill.put(new BigDecimal("2"), new Cash(new BigDecimal("2"), 5, 5, new Date(), new Date()));
        temporaryTill.put(new BigDecimal("1"), new Cash(new BigDecimal("1"), 5, 5, new Date(), new Date()));
        temporaryTill.put(new BigDecimal("0.5"), new Cash(new BigDecimal("0.5"), 5, 5, new Date(), new Date()));
        temporaryTill.put(new BigDecimal("0.2"), new Cash(new BigDecimal("0.2"), 5, 5, new Date(), new Date()));
        temporaryTill.put(new BigDecimal("0.1"), new Cash(new BigDecimal("0.1"), 5, 5, new Date(), new Date()));
    }
}
