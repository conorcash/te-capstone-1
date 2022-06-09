package com.techelevator;

public class Chips extends Item{
    private final String MESSAGE = "Crunch Crunch, Yum!";

    public Chips (double price, String name,String address,int quantity) {
        super(price,name,address,quantity);
    }

    @Override
    public String getMessage() {
        return MESSAGE;
    }

}
