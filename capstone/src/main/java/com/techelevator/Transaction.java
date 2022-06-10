package com.techelevator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Transaction {
    private BigDecimal balance = BigDecimal.ZERO;
    private final BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);
    private Log log = new Log();

    public enum Coin {
        QUARTER,
        DIME,
        NICKLE
    }

    public Transaction () {

    }

    public void feedMoney (BigDecimal tendered) {
        this.balance = this.balance.add(tendered);
        log.logEntry("FEED MONEY:",tendered,balance);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Map<Coin,Integer> getChangeMap() {
        Map<Coin,Integer> changeMap = new HashMap<>();
        BigDecimal change = getBalance();
        int cents = change.multiply(ONE_HUNDRED).intValue();
        int quarters = cents / 25;
        int dimes = (cents % 25) / 10;
        int nickles = ((cents % 25) % 10) / 5;

        changeMap.put(Coin.QUARTER,quarters);
        changeMap.put(Coin.DIME,dimes);
        changeMap.put(Coin.NICKLE,nickles);

        String action = "GIVE CHANGE:";

        this.balance = BigDecimal.ZERO;
        log.logEntry(action,change,balance);
        return changeMap;
    }

    public boolean buy (String address, Inventory inventory) {
        Item item = inventory.getItem(address);
        BigDecimal cost = item.getPrice();
        if (getBalance().compareTo(cost) > -1 && item.buy()) {
            this.balance = this.balance.subtract(cost);
            String action = item.getName() + " " + item.getAddress();
            log.logEntry(action,cost,balance);
            return true;
        }
        return false;
    }
}
