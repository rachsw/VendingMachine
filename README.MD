Assumptions
- only one item is bought at a time? e.g how can multiple people buy from the same vending machine at the same time?
- Currency is not an issue, can account for it later? e.g validation for currency being the correct currency
- We won't validate the operator providing the wrong change in e.g adding 0.3? do we validate that?
- supported coin types should be seen as immutable for any given instance of the vending machine

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
