package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Log {

    private final String PATH = "Log.txt";

    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    String date = dateFormat.format(LocalDate.now());

    DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mm:ss a");
    String time = timeFormat.format(LocalTime.now());

    File log = new File(PATH);

    FileOutputStream fos = null;

    public Log () {
        log.delete();
        try {
            fos = new FileOutputStream(log, true);
        } catch (FileNotFoundException fnf) {
            System.out.println(fnf.getMessage());
        }
    }

    public void logEntry (String action, BigDecimal amount, BigDecimal balance) {
        PrintWriter logger = new PrintWriter(fos);
        logger.printf("%s %s %s $%.2f $%.2f\n",date,time,action,amount,balance);
        logger.flush();
    }
}
