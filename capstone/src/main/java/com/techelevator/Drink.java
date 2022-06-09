package com.techelevator;

import java.math.BigDecimal;

public class Drink extends Item{
    private final String MESSAGE = "Glug Glug, Yum!";

    public Drink (double price, String name,String address,int quantity) {
        super(price,name,address,quantity);
    }

    @Override
    public String getMessage() {
        return MESSAGE;
    }

}
