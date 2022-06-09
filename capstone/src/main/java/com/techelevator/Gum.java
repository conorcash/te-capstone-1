package com.techelevator;

public class Gum extends Item{
    private final String MESSAGE = "Chew Chew, Yum!";

    public Gum (double price, String name,String address,int quantity) {
        super(price,name,address,quantity);
    }

    @Override
    public String getMessage() {
        return MESSAGE;
    }

}
