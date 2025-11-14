
[Instructions.MD](Instructions.MD)

Assumptions
- only one managedProduct is bought at a time? e.g how can multiple people buy from the same vending machine at the same time?
- Currency is not an issue, can account for it later? e.g validation for currency being the correct currency
- We won't validate the operator providing the wrong change in e.g adding 0.3, but I would in the future validate the operator has valid coins for updating the machine 
- supported coin types should be seen as immutable for any given instance of the vending machine
- a vending machine starts out with product slots and then an operator adds products to each stock.

Out of scope
- transactions, not keeping track of the transaction history or any history of coins stored.
- keeping track of when the machine was updated and when products were tracked. let's pretend that was done externally.
- tracking the limitations of coins. to keep it simple for calculating change we assume no limitations on how many coins the vending machine can store
- Using frameworks and libraries. I went with mostly just core java but lombok for example could be used for model creation

###  Giving different change back
It is possible to overpay the vending machine by a certain amount of coins and then get a whole other set of coins back
this is because i would imagine in a normal scenario we want to optimise the amount of coins being returned 
to the client by giving fewer coins as possible starting from the larger coins first. 
This means we can maintain a stock of coins betters e.g giving back 1p instead of 10 10p seems more optimised.


### How to instantiate the VendingMachine
To build
``` mvn clean package```

### Setting up the machine
The Vending Machine is instantiated by calling new VendingMachine({AmountOfProductAvailable}, List[CoinItems]}), where coin items are new CoinItem({value of the coin}, {stock of the coin})
e.g for a vending Machine that can have 5 products, and coins representing 2pounds = 200, 1pound = 100a and 50 = 50pence
```
VendingMachine vendingMachine = new VendingMachine(5, List.of(
    new CoinItem(200,  10),
    new CoinItem(100,  10),
    new CoinItem(50,  10),
    new CoinItem(20,  10),
    new CoinItem(10, 10)));
```
To add items to the machine an operator must create the Items
```declarative
        vendingMachine.createItem(new ManagedProduct("A1 Coke", 140, 20));
        vendingMachine.createItem(new ManagedProduct("A2 Sprite", 130, 20));
        vendingMachine.createItem(new ManagedProduct("A3 Chocolate", 150, 20));
        vendingMachine.createItem(new ManagedProduct("A4 Coke Zero", 150, 20));
        vendingMachine.createItem(new ManagedProduct("A5 Coke Diet", 150, 20));
```

and add stock of the item as it defaults to 0 (assuming the operator has to stock after instiating)

```declarative
 vendingMachine.updateItemStock("A1 Coke", 5);
vendingMachine.updateItemStock("A2 Sprite", 5);
vendingMachine.updateItemStock("A3 Chocolate", 5);
vendingMachine.updateItemStock("A4 Coke Zero", 5);
vendingMachine.updateItemStock("A5 Coke Diet", 5);
```

### Using the machine

To Check available products 
```declarative
vendingMachine.viewProducts()
```
to purchase a product and get the change back
```declarative
var change = vendingMachine.purchaseProduct("A1 Coke", List.of(
                new CoinItem(100, 2)));
```
The till and product stock should update accordingly

This is simply due to time constraints.
The VendingMachine implements 2 interfaces: [ConsumerInterface.java](src/main/java/org/example/interfacepackage/ConsumerInterface.java) and [OperatorInterface.java](src/main/java/org/example/interfacepackage/OperatorInterface.java)

## The operator interface
This is assuming a maintenance person is maintaining the coin and product stock.
They are able to do the following:
- getItems, removeItems, updateItemPrice, updateItemStock, createItems
- getTillContents
- updateStock of coins

## The consumer interface
This is someone who wants to buy an item
They are able to do the following:
- viewProducts, view product (e.g get the price for a particular product) 
- purchase a product 

I have structured my models by the following:

**CoinItem**: this item represents a coin value and how many of these coins we have given the machine. This is so we can provide and give change to the user. e.g coin = 20p and I can have 5 of these inputted in the machine

**Product**: This is a vendingMachine product which consists of an Id (A1, A2 etc), price and how much stock the Vending machine has of it.
This item is the parent item and has all the fields I want to expose to both the consumer and the operator. 
The Operator on the other hand can see and store a
**ManagedProduct**: This is so we can store things like product limits and control how many of a product goes into the machine. 

## The databases
My coins are stored inside of a CashRegisterService e.g A map of CoinValues and their stock. This service also contains my helper methods to calculate things and track change etc.
My products are stored inside of a product database which tracks the limits to amount of products a vending machine can have. 
This product database is more of a typical operational one where you can create, read, update, delete.

### Error cases
The Vending Machine supports a series of error scenarios these may include the following
- Product limit has been met on create. This is because the Vending machine can only support up to X amount of products -> throws IllegalStateException
- Purchasing a product that is out of stock -> throws IllegalStateException
- item already exists. Trying to create an item that is already in the machine -> IllegalArgumentException 
- Product stock limit has been met -> IllegalStateException, cannot overstock the machine
- Product item does not exist -> IllegalArgumentException
- Not enough cash has been provided to purchase a product -> IllegalArgumentException
- Not enough cash in the till to provide the user with change -> IllegalStateException
- Input coin validation for the user e.g wrong coins used that machine does not accept -> IllegalArgumentException 

### Drawbacks
1. Because of the complexity of the cash register implementation. I found there were a few scenarios and things I wanted to ensure
   - Atomic operations, I did not want to insert coins in the till, only for the till to have insufficient funds for change, or coins were incorrect leading to a rollback in undoing the coin insertion
   - I wanted to implement a way to be able to calculate coins needed for change and check the till had the right change before writing to the "till database"
   - This is done by making a copy of the existing till, adding the proposed change in from the user after validating the change contents and then writing the updates to the till after we've calculated the correct change.
2. I enclosed all till operations inside of one service so i could utilise a synchronized lock this was to enable me to lock the service and prevent a possiblity of threads trying to conditionally write to the till while I was calculating and validating the change.
   - this however does not solve all race conditions as this introduces an issue with my product DB not staying consistent in case the till has a service issue we could possibly accidentally update a product without checking the till transaction was successful.

###  Test coverage

I have added test coverage to validate some common scenarios and to validate how i believe some methods should work
These tests also demonstrate how I might  unit test my system but definitely they don't cover all scenarios and are very lightly asserting so i could check me methods worked.

### Given more time
1. Split out the classes further. It is not ideal to have all the cashregister service containing transaction methods + helper methods and the coin till storage. This was only so I could bundle the transaction of calculating coins and ensuring i could isolate the method changes. 
2. Full implement more functionality, currently we can have an unlimited number of coins stored.
3. Have a proper coin storage that does not sit in a service. 