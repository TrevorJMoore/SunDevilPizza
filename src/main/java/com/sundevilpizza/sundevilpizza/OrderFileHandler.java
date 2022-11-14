package com.sundevilpizza.sundevilpizza;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class OrderFileHandler {

    private static File orderFile;

    public OrderFileHandler() {
        try {
            orderFile = new File("orders.txt");
            if (orderFile.createNewFile()) {
                System.out.println("File created: " + orderFile.getName());
            } else {
                System.out.println(orderFile.getName() + " already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getAmtOrders() {
        int amt = 0;
        String data = "";
        try {
            Scanner reader = new Scanner(orderFile);
            while (reader.hasNextLine()) {
                data = reader.nextLine();
                System.out.println(data);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int idx = 0; idx < data.length(); idx++) {
            if (data.charAt(idx) == '\\' ) {
                amt++;
                System.out.println(amt);
            }
        }

        return amt;

    }

    public void addOrder(ShoppingCart s) {
        try {
            FileWriter write = new FileWriter("orders.txt", true);
            write.write(s.fileString());
            write.write("\\\n");
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
