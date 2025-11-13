package org.example.model;

public class ManagedProduct extends Product
 {
     //might also have type etc in the future
     //the limit of items we can have for this.
     int limit;

     public ManagedProduct(String id, int price, int limit) {
         //when you create a new item in the system it needs to default to 0 as we have not yet stocked the machine
         super(id, price, 0);
         this.limit = limit;
     }

     public int getLimit() {
         return limit;
     }

     public void setStock(int stock) {
         this.stock = stock;
     }

     public void setPrice(int price) {
         this.price = price;
     }

 }
