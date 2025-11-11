package org.example.model;

import java.util.Date;

public record Product(
        String id,
        //might also have type etc in the future
        Double price,
        int stock,
        int limit
) {
}
