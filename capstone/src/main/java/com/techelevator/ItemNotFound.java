package com.techelevator;

public class ItemNotFound extends Exception {

    public ItemNotFound () {
        super("Item not found.");
    }

    public ItemNotFound (String message) {
        super(message);
    }

}
