package org.example.model;

import java.math.BigDecimal;
import java.util.Date;

public record Cash(
        BigDecimal value,
        int stock,
        int limit,
        Date createdAt,
        Date updatedAt
) {
}
