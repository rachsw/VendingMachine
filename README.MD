
[Instructions.MD](Instructions.MD)

Assumptions
- only one managedProduct is bought at a time? e.g how can multiple people buy from the same vending machine at the same time?
- Currency is not an issue, can account for it later? e.g validation for currency being the correct currency
- We won't validate the operator providing the wrong change in e.g adding 0.3? do we validate that?
- supported coin types should be seen as immutable for any given instance of the vending machine

Out of scope
- transactions 
- keeping track of when the machine was updated and when products were tracked. let's pretend that was done externally.
- tracking the limitations of coins. to keep it simple for calculating change we assume no limitations on how many coins the vending machine can store

Questions
Do we want to store each transaction ? e.g a log of everything that has been purchased?
What to do about too much cash
Coins available (there are no notes)
0.10, 0.20, 0.50, 1, 2

###  Giving different change back
It is possible to overpay the vending machine by a certain amount of coins and then get a whole other set of coins back
this is because i would imagine in a normal scenario we want to optimise the amount of coins being returned 
to the client by giving fewer coins as possible starting from the larger coins first. 
This means we can maintain a stock of coins betters e.g giving back 1p instead of 10 10p seems more optimised.

Things to do:
 - Instantiation of the Vending machine. e.g instantiate vending machine with x amount of products and a list of coins and their stock.
 - Products, storing the products, creating new products etc
 - Cash, Figuring out change, checking the till if the change exists, adding new change. we need to add change to db and then return new set of change


1. track number of items
   2. set number of items 
   3. get number of items 
   4. set product price
   5. get product price
   6. set nunmber of coins
   7. get number of coins
      IllegalStateException - cant have the machine in that state
   8. IllegalArgumentException - product doesn't exist.
   9. if the parameters are invalid (non-existing managedProduct slot referenced, not enough money provided).
2. track change
3. return correct change
4. 2 interfaces
   5. get price of product
   6. buy product, accepts slot identifier, coins, returns coins