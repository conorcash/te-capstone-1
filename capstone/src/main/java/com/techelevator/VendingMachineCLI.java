package com.techelevator;

import java.math.BigDecimal;
import java.util.*;

public class VendingMachineCLI {

	public VendingMachineCLI() {
	}

	public static void main(String[] args) {
		VendingMachineCLI cli = new VendingMachineCLI();
		cli.run();

		Inventory inventory = new Inventory();

		BigDecimal balance = BigDecimal.ZERO;
		Scanner userInput = new Scanner(System.in);
		int mainMenuInput = 0;
		int purchaseMenuInput = 0;
		BigDecimal tendered;
		final String INVALID_SELECTION = "That's not a valid selection.\n";
		final String OUT_OF_STOCK = "Item out of stock.\n";
		final String INSUFFICIENT_FUNDS = "Item price exceeds available balance.\n";


		final String MAIN_MENU = ("(1) Display Vending Machine Items\n" +
				"(2) Purchase\n" +
				"(3) Exit");

		final String PURCHASE_MENU = ("(1) Feed Money\n" +
				"(2) Select Product\n" +
				"(3) Finish Transaction");

		while (true) {
			System.out.println(MAIN_MENU);
			try {
				mainMenuInput = Integer.parseInt(userInput.nextLine());
			} catch (NumberFormatException nfe) {
				System.out.println(INVALID_SELECTION);
				continue;
			}
			if ( mainMenuInput == 1) {
				System.out.println(displayInventory(inventory));
			} else if (mainMenuInput == 2) {
				Transaction transaction = new Transaction(inventory);
				while (true) {
					System.out.printf("Current money provided: $%.2f\n", balance);
					System.out.println(PURCHASE_MENU);
					try {
						purchaseMenuInput = Integer.parseInt(userInput.nextLine());
					} catch (NumberFormatException nfe) {
						System.out.println(INVALID_SELECTION);
						continue;
					}
					if (purchaseMenuInput == 1) {
						System.out.print("Enter whole dollar amount you wish to use: ");
						try {
							tendered = BigDecimal.valueOf(Integer.parseInt(userInput.nextLine()));
						} catch (NumberFormatException nfe) {
							System.out.println(INVALID_SELECTION);
							continue;
						}
						transaction.feedMoney(tendered);
						balance = transaction.getBalance();
					} else if (purchaseMenuInput == 2) {
						System.out.println(displayInventory(inventory));
						String address = userInput.nextLine();
						selection(transaction,inventory,address);
					} else if (purchaseMenuInput == 3) {
						exit(transaction);
						balance = transaction.getBalance();
						break;
					} else {
						System.out.println(INVALID_SELECTION);
					}
				}
			} else if (mainMenuInput == 3) {
				break;
			} else if (mainMenuInput == 4) {
				Report report = new Report(inventory);
				System.out.println(report.getFileName());
			} else {
				System.out.println(INVALID_SELECTION);
			}
		}
	}

	public void run() {
	}

	public static String displayInventory (Inventory inventory) {
		String displayInventory = "";
		List<String> addressList = new ArrayList<>(inventory.getInventory().keySet());
		addressList.sort(null);

		for (String address : addressList) {
			String name = inventory.getItem(address).getName();
			BigDecimal price = inventory.getItem(address).getPrice();
			int quantity = inventory.getItem(address).getQuantity();
			String quantityOut = (quantity > 0 ? "Qty: " + Integer.toString(quantity) : "SOLD OUT");

			String itemInfo = String.format("%s - %s - $%.2f - %s\n",address,name,price,quantityOut);
			displayInventory += itemInfo;
		}
		return displayInventory;
	}

	public static void exit (Transaction transaction) {
		transaction.endTransaction();
		Map<Transaction.Coin,Integer> changeMap = transaction.getChangeMap();
		int quarters = changeMap.get(Transaction.Coin.QUARTER);
		int dimes = changeMap.get(Transaction.Coin.DIME);
		int nickles = changeMap.get(Transaction.Coin.NICKLE);
		String change = String.format("Quarters: %d\nDimes: %d\nNickles: %d\n",quarters,dimes,nickles);
		System.out.printf("\n***TRANSACTION COMPLETE***\nChange Returned:\n%s\nThank you, come again.\n",
				change);
		System.out.println("**************************");
	}

	public static void selection (Transaction transaction, Inventory inventory, String address) {
		try {
			transaction.buy(address);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
			Item item = inventory.getItem(address);
			String name = item.getName();
			BigDecimal price = item.getPrice();
			String message = item.getMessage();
			BigDecimal balance = transaction.getBalance();
			System.out.printf("\n***DISPENSED***\nItem: %s\nCost: $%.2f\nRemaining Balance: $%.2f\n%s\n",
					name,price,balance,message);
			System.out.println("***************");
	}

}
