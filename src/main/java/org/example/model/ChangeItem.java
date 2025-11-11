package org.example.model;

import java.math.BigDecimal;

public record ChangeItem(
        BigDecimal value,
        int stock
) {
}
