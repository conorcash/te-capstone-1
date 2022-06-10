package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class TransactionTests {


    @Test
    public void feed_money_increase_balance () {
        Transaction transaction = new Transaction();

        BigDecimal moneyFed = BigDecimal.TEN;

        transaction.feedMoney(moneyFed);

        BigDecimal expected = transaction.getBalance();

        Assert.assertEquals(moneyFed,expected);
    }

    @Test
    public void overall_change_and_balance_test () {
        Transaction transaction = new Transaction();
        Inventory inventory = new Inventory();
        BigDecimal moneyFed = BigDecimal.TEN;
        String selection = "A1";
        BigDecimal expected = BigDecimal.valueOf(6.95);

        transaction.feedMoney(moneyFed);

        transaction.buy(selection,inventory);

        BigDecimal change_and_balance = transaction.getBalance();

        Assert.assertEquals(expected,change_and_balance);
    }

    @Test
    public void item_quantity_reduced_when_bought () {
        Transaction transaction = new Transaction();
        Inventory inventory = new Inventory();
        BigDecimal moneyFed = BigDecimal.TEN;
        String selection = "A1";

        transaction.feedMoney(moneyFed);
        transaction.buy(selection,inventory);
        int quantity = inventory.getItem(selection).getQuantity();
        int expected = 4;

        Assert.assertEquals(expected,quantity);
    }

    @Test
    public void proper_coin_distribution_change () {
        Transaction transaction = new Transaction();
        Inventory inventory = new Inventory();
        BigDecimal moneyFed = BigDecimal.TEN;
        String selection = "A1";
        Map<Transaction.Coin,Integer> expected = new HashMap<>();

        transaction.feedMoney(moneyFed);
        transaction.buy(selection,inventory);
        expected.put(Transaction.Coin.QUARTER,27);
        expected.put(Transaction.Coin.DIME,2);
        expected.put(Transaction.Coin.NICKLE,0);
        Map<Transaction.Coin,Integer> changeMap = transaction.getChangeMap();

        Assert.assertEquals(expected,changeMap);
    }

    @Test
    public void correct_item_from_address () {
        Inventory inventory = new Inventory();
        String selection = "A1";
        Item potatoCrisps =  new Chips(3.05,"Potato Crisps","A1",5);

        Item A1 = inventory.getItem(selection);

        Assert.assertEquals(potatoCrisps.getPrice(),A1.getPrice());
        Assert.assertEquals(potatoCrisps.getName(),A1.getName());
        Assert.assertEquals(potatoCrisps.getAddress(),A1.getAddress());
        Assert.assertEquals(potatoCrisps.getQuantity(),A1.getQuantity());
    }

}
