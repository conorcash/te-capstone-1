package com.techelevator;

public class Candy extends Item{
    private final String MESSAGE = "Munch Munch, Yum!";

    public Candy (double price, String name,String address,int quantity) {
        super(price,name,address,quantity);
    }

    @Override
    public String getMessage() {
        return MESSAGE;
    }

}
