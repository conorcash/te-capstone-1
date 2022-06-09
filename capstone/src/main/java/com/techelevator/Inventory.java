package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Inventory {
    private File importFile = new File("vendingmachine.csv");
    private final int STARTING_QUANTITY = 5;
    private Map<String, Item> inventory = new HashMap<>();

    private Scanner read = null;

    public Inventory () {
        try {
            read = new Scanner(importFile);
        } catch (FileNotFoundException fnf) {
            System.out.println(fnf.getMessage());
        }

        String[] lineArray;
        while (read.hasNextLine()) {
            lineArray = read.nextLine().split("\\|");
            String address = lineArray[0];
            String name = lineArray[1];
            double price = Double.parseDouble(lineArray[2]);
            String type = lineArray[3];

            if (type.equals("Drink")) {
                inventory.put(address,new Drink(price, name, address, STARTING_QUANTITY));
            } else if (type.equals("Candy")) {
                inventory.put(address,new Candy(price, name, address, STARTING_QUANTITY));
            } else if (type.equals("Gum")) {
                inventory.put(address,new Gum(price, name, address, STARTING_QUANTITY));
            } else {
                inventory.put(address,new Chips(price, name, address, STARTING_QUANTITY));
            }
        }
    }

    public Item getItem (String input) {
        return inventory.get(input);
    }

    public Map<String, Item> getInventory() {
        return inventory;
    }

    public boolean itemExists (String address) {
        return getItem(address) != null;
    }

    public boolean itemInStock (String address) {
        return getItem(address).getQuantity() > 0;
    }
}
