package org.example.service;

import org.example.BadInput;
import org.example.model.CashConfiguration;
import org.example.model.ChangeItem;

import java.math.BigDecimal;
import java.util.*;

public class CashService {
    //Will order the keys
    Map<BigDecimal, CashConfiguration> temporaryTill = new TreeMap<>(Comparator.reverseOrder());


    public CashService() {
        setupTill();
    }

    public CashService(List<CashConfiguration> cashConfigurations) {
        setupTill(cashConfigurations);
    }

    // note we need to ensure there is enough change in the till before returning it
    public List<ChangeItem> getChange(List<ChangeItem> cashGiven, BigDecimal price) throws Exception {

        for (ChangeItem cash : cashGiven) {
            price = price.subtract(cash.value().multiply(BigDecimal.valueOf(cash.stock())));
        }
        if(price.compareTo(BigDecimal.ZERO) == 0) {
            return new ArrayList<>();
        }
        //overpaid
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            return calculateCoins(price.abs());
        }
        else {
            throw new BadInput("Not enough cash provided ");
        }
    }
   private List<ChangeItem> calculateCoins(BigDecimal amountLeft) {
        List<ChangeItem> change = new ArrayList<>();
        // lookup algorithm for returning change
        //1.50
        //We need the keyset to be ordered from highest to lowest
        for (BigDecimal key : temporaryTill.keySet()) {
           int stock = 0;

            while (amountLeft.compareTo(key) >= 0) {
                    amountLeft = amountLeft.subtract(key);
                    stock++;
                change.add(new ChangeItem(key, stock));
            }
            if (amountLeft.compareTo(BigDecimal.ZERO) == 0) {
                break;
            }
        }
        return change;
    }

    void setupTill() {
        temporaryTill.put(new BigDecimal("2"), new CashConfiguration(new BigDecimal("2"), 5,5));
        temporaryTill.put(new BigDecimal("1"), new CashConfiguration(new BigDecimal("1"), 5,  5));
        temporaryTill.put(new BigDecimal("0.5"), new CashConfiguration(new BigDecimal("0.5"), 5,  5));
        temporaryTill.put(new BigDecimal("0.2"), new CashConfiguration(new BigDecimal("0.2"), 5, 5));
        temporaryTill.put(new BigDecimal("0.1"), new CashConfiguration(new BigDecimal("0.1"), 5,  5));
    }

    void setupTill(List<CashConfiguration> configurations) {
        for (CashConfiguration configuration : configurations) {
            temporaryTill.put(new BigDecimal(configuration.value().toString()), configuration);
        }
    }
}
