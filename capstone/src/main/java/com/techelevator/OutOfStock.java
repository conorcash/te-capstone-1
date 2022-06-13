package com.techelevator;

public class OutOfStock extends Exception{

    public OutOfStock () {
        super("Item out of stock.");
    }
}
