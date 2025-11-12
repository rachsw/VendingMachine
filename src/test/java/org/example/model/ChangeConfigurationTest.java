package org.example.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ChangeConfigurationTest {

    @Test
    void createCashObject() {
        var cashObject = new ChangeConfiguration(10, 10, 10);

        assertNotNull(cashObject);
    }
}