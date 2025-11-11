package org.example.model;

import java.util.Date;

public record Product(
        String id,
        Double price,
        int stock,
        Date createdAt,
        Date updatedAt,
        int limit,
        String type
) {
}
