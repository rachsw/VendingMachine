package org.example.service;


import org.example.model.ChangeConfiguration;
import org.example.model.ChangeItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CashRegisterServiceTest {

    final CashRegisterService cashRegisterService = new CashRegisterService();

    @Test
    void getNoChangeForExactAmount() throws Exception {
        var expected = new ArrayList<>();

        var actual = cashRegisterService.getChange(Arrays.asList(
                new ChangeItem(20, 5),
                new ChangeItem(100, 1),
                new ChangeItem(50, 1)), 250);
        assertEquals(actual, expected);
    }

    @Test
    void canGiveCorrectChange() throws Exception {
        var expected = List.of(new ChangeItem(50, 1));

        //$3
        var actual = cashRegisterService.getChange(List.of(
                new ChangeItem(100, 3)), 250);
        assertEquals(expected, actual);
    }

    @Test
    void canGiveCorrectChangeMultiCoin() throws Exception {
        var expected = List.of(
                new ChangeItem(100, 1),
                new ChangeItem(50, 1)
                );

        //$3
        var actual = cashRegisterService.getChange(List.of(
                new ChangeItem(200, 2)), 250);
        assertEquals(expected, actual);
    }

    @Test
    void canGiveCorrectChangeAllCoins() throws Exception {
        var expected = List.of(
                new ChangeItem(200, 1),
                new ChangeItem(100, 1),
                new ChangeItem(50, 1),
                new ChangeItem(20, 1),
                new ChangeItem(10, 1)
        );

        //need to fix this rounding issue!
        var actual = cashRegisterService.getChange(List.of(
                new ChangeItem(200, 3),
                new ChangeItem(100, 2)), 420);
        assertEquals(expected, actual);
    }

    @Test()
    void canThrowIfUserHasUnderpaid() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> cashRegisterService.getChange(List.of(
                new ChangeItem(200, 1)), 420));
    }

    @Test()
    void canThrowIfNotEnoughChangeInTill() {
        CashRegisterService cashRegister = new CashRegisterService(List.of(
                new ChangeConfiguration(50, 5)));

        Assertions.assertThrows(IllegalStateException.class, () -> cashRegister.getChange(List.of(
                new ChangeItem(200, 4)), 420));
    }


}