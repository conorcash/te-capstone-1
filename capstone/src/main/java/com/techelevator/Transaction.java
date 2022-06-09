package com.techelevator;

import java.math.BigDecimal;

public class Transaction {
    private BigDecimal balance = BigDecimal.ZERO;
    private BigDecimal total = BigDecimal.ZERO;
    private BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);

    public Transaction () {
    }

    public void feedMoney (BigDecimal tendered) {
        this.balance = this.balance.add(tendered);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public String getChange() {
        int cents = getBalance().multiply(ONE_HUNDRED).intValue();
        int quarters = cents / 25;
        int dimes = (cents % 25) / 10;
        int nickles = ((cents % 25) % 10) / 5;
        this.balance = BigDecimal.ZERO;

        return String.format("Quarters: %d\nDimes: %d\nNickles: %d\n",quarters,dimes,nickles);
    }

    public boolean charge (BigDecimal cost) {
        if (getBalance().compareTo(cost) > -1) {
            this.balance = this.balance.subtract(cost);
            this.total = this.total.add(cost);
            return true;
        }
        return false;
    }

    public boolean selection(String address, Inventory inventory) {
        Item item = inventory.getItem(address);
        if(item.buy()) {
            return charge(item.getPrice());
        }
        return false;
    }
}
