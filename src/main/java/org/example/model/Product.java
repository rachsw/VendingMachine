package org.example.model;

import java.util.Date;

public class Product
 {
     String id;
     //might also have type etc in the future
     Double price;
     int stock;
     int limit;

     public Product(String id, Double price, int limit) {
         this.id = id;
         this.price = price;
         this.stock = 0;
         this.limit = limit;
     }

     public String getId() {
         return id;
     }

     public void setId(String id) {
         this.id = id;
     }

     public Double getPrice() {
         return price;
     }

     public void setPrice(Double price) {
         this.price = price;
     }

     public int getStock() {
         return stock;
     }

     public void setStock(int stock) {
         this.stock = stock;
     }

     public int getLimit() {
         return limit;
     }

     public void setLimit(int limit) {
         this.limit = limit;
     }
 }
