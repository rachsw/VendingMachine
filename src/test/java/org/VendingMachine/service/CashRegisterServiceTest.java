package org.VendingMachine.service;


import org.VendingMachine.model.CoinItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CashRegisterServiceTest {

    final CashRegisterService cashRegisterService = new CashRegisterService();

    @Test
    void canGiveCorrectChange() {
        Map<Integer, Integer> coinsAvailable = new HashMap<>();
        coinsAvailable.put(200, 10);
        coinsAvailable.put(100, 13);
        coinsAvailable.put(50, 10);
        coinsAvailable.put(20, 10);
        coinsAvailable.put(10, 10);

        List<CoinItem> expected = List.of(
                new CoinItem(200, 1),
                new CoinItem(50, 1));

        List<CoinItem> actual = cashRegisterService.calculateChangeToReturn(coinsAvailable, 250);
        assertEquals(expected, actual);
    }

    @Test
    void canGiveCorrectChangeMultiCoin() {
        Map<Integer, Integer> coinsAvailable = new HashMap<>();
        coinsAvailable.put(200, 2);
        coinsAvailable.put(100, 2);
        coinsAvailable.put(50, 2);
        coinsAvailable.put(10, 2);

        List<CoinItem> expected = List.of(
                new CoinItem(100, 1),
                new CoinItem(50, 1),
                new CoinItem(10, 2)
        );

        List<CoinItem> actual = cashRegisterService.calculateChangeToReturn(coinsAvailable, 170);
        assertEquals(expected, actual);
    }

    @Test
    void canGiveCorrectChangeAllCoins() {
        Map<Integer, Integer> coinsAvailable = new HashMap<>();
        coinsAvailable.put(200, 3);
        coinsAvailable.put(100, 2);
        coinsAvailable.put(50, 2);
        coinsAvailable.put(20, 2);
        coinsAvailable.put(10, 2);

        List<CoinItem> expected = List.of(
                new CoinItem(200, 1),
                new CoinItem(100, 1),
                new CoinItem(50, 1),
                new CoinItem(20, 1),
                new CoinItem(10, 1)
        );

        List<CoinItem> actual = cashRegisterService.calculateChangeToReturn(coinsAvailable, 380);
        assertEquals(expected, actual);
    }

    @Test
    void canThrowIfUserHasUnderpaid() {
        var coinList = List.of(new CoinItem(50, 1));

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                cashRegisterService.processCoinTransaction(coinList, 420));
    }

    @Test
    void canThrowIfUserHasCashInvalid() {
        var coinList = List.of(new CoinItem(12, 1));

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                cashRegisterService.processCoinTransaction(coinList, 420));
    }

    @Test
    void canThrowIfNotEnoughChangeInTill() {
        CashRegisterService cashRegister = new CashRegisterService(List.of(
                new CoinItem(50, 5)
        ));

        Map<Integer, Integer> coinsAvailable = new HashMap<>();
        coinsAvailable.put(200, 4);

        Assertions.assertThrows(IllegalStateException.class, () ->
                cashRegister.calculateChangeToReturn(coinsAvailable, 420));
    }
}