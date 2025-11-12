package org.example.service;

import org.example.exceptions.BadInput;
import org.example.model.ChangeItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CashRegisterServiceTest {

    final CashRegisterService cashRegisterService = new CashRegisterService();

//    @Test
//    void getNoChangeForExactAmount() throws Exception {
//        var expected = new ArrayList<>();
//
//        var actual = cashRegisterService.getChange(Arrays.asList(
//                new ChangeItem(new BigDecimal("0.2"), 5),
//                new ChangeItem(new BigDecimal("1"), 1),
//                new ChangeItem(new BigDecimal("0.5"), 1)), BigDecimal.valueOf(2.50));
//        assertEquals(actual, expected);
//    }
//
//    @Test
//    void canGiveCorrectChange() throws Exception {
//        var expected = List.of(new ChangeItem(new BigDecimal("0.5"), 1));
//
//        //$3
//        var actual = cashRegisterService.getChange(List.of(
//                new ChangeItem(new BigDecimal("1"), 3)), BigDecimal.valueOf(2.50));
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void canGiveCorrectChangeMultiCoin() throws Exception {
//        var expected = List.of(
//                new ChangeItem(new BigDecimal(1), 1),
//                new ChangeItem(new BigDecimal("0.5"), 1)
//                );
//
//        //$3
//        var actual = cashRegisterService.getChange(List.of(
//                new ChangeItem(new BigDecimal(2), 2)), BigDecimal.valueOf(2.50));
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void canGiveCorrectChangeAllCoins() throws Exception {
//        var expected = List.of(
//                new ChangeItem(new BigDecimal(2), 1),
//                new ChangeItem(new BigDecimal(1), 1),
//                new ChangeItem(new BigDecimal("0.5"), 1),
//                new ChangeItem(new BigDecimal("0.2"), 1),
//                new ChangeItem(new BigDecimal("0.1"), 1)
//        );
//
//        //need to fix this rounding issue!
//        var actual = cashRegisterService.getChange(List.of(
//                new ChangeItem(new BigDecimal(2), 3),
//                new ChangeItem(new BigDecimal(1), 2)), BigDecimal.valueOf(4.20));
//        assertEquals(expected, actual);
//    }
//
//    @Test()
//    void canThrowIfUserHasUnderpaid() {
//
//        Assertions.assertThrows(BadInput.class, () -> cashRegisterService.getChange(List.of(
//                new ChangeItem(new BigDecimal(2), 1)), BigDecimal.valueOf(4.20)));
//    }
}