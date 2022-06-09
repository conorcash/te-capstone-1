package com.techelevator;

import java.math.BigDecimal;

public abstract class Item {
    private BigDecimal price;
    private String name;
    private String address;
    private String message;
    private int quantity;

    public Item(double price,String name,String address, int quantity) {
        this.price = BigDecimal.valueOf(price);
        this.name = name;
        this.address = address;
        this.quantity = quantity;
    }

    public String getMessage() {
        return message;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getQuantity() {
        return quantity;
    }

    private void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean buy () {
        int stock = getQuantity();
        if (stock > 0) {
            setQuantity(stock - 1);
            return true;
        }
        return false;
    }
}
