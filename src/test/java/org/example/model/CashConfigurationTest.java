package org.example.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CashConfigurationTest {

    @Test
    void createCashObject() {
        var cashObject = new CashConfiguration(  new BigDecimal("0.1"), 10, 10);

        assertNotNull(cashObject);
    }
}