package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Report {

    private final BigDecimal INITIAL_QUANTITY = BigDecimal.valueOf(5);

    DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("(MM-dd-yyyy hh-mm-ss-a)");
    String stamp = dateTimeFormat.format(LocalDateTime.now());
    String fileName;

    FileOutputStream fos = null;

    public Report (Inventory inventory) {
        fileName = "report " + stamp + ".txt";
        File report = new File(fileName);
        List<String> addressList = new ArrayList<>(inventory.getInventory().keySet());
        addressList.sort(null);
        BigDecimal total = BigDecimal.ZERO;
        String output = "";

        for (String addressItem : addressList) {
            String name = inventory.getItem(addressItem).getName();
            BigDecimal price = inventory.getItem(addressItem).getPrice();
            BigDecimal quantity = BigDecimal.valueOf(inventory.getItem(addressItem).getQuantity());
            BigDecimal sold = INITIAL_QUANTITY.subtract(quantity);
            BigDecimal sales = sold.multiply(price);

            output += String.format("%s|%.0f\n",name,sold);
            total = total.add(sales);
        }

        String totalString = String.format("\n**TOTAL SALES** $%.2f",total);

        try {
            fos = new FileOutputStream(report, true);
        } catch (FileNotFoundException fnf) {
            System.out.println(fnf.getMessage());
        }

        PrintWriter reportWriter = new PrintWriter(fos);
        reportWriter.print(output + totalString);
        reportWriter.close();
    }

    public String getFileName() {
        return fileName;
    }
}
