package org.example.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CashTest {

    @Test
    void createCashObject() {
        var cashObject = new Cash(  new BigDecimal("0.1"), 10, 10, new Date(), new Date());

        assertNotNull(cashObject);
    }
}