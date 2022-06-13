package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {

    private final String PATH = "Log.txt";

    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
    String stamp = dateFormat.format(LocalDateTime.now());

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
        logger.printf("%s %s $%.2f $%.2f\n",stamp,action,amount,balance);
        logger.flush();
    }
}