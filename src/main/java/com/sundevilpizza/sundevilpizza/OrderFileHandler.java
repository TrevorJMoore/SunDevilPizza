package com.sundevilpizza.sundevilpizza;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class OrderFileHandler {

    private static File orderFile;
    private static ArrayList<ShoppingCart> allOrders = new ArrayList<>();


    //Create the order file if not already created.
    public OrderFileHandler() {
        try {
            orderFile = new File("orders.txt");
            if (orderFile.createNewFile()) {
                System.out.println("File created: " + orderFile.getName());
            } else {
                System.out.println(orderFile.getName() + " already exists.");
                //Obtain all cached orders and fill them
               // populateOrders();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ShoppingCart> getAllOrders() {
        return allOrders;
    }

    private void populateOrders() {
        String data = "";
        int positionInOrder = 0;
        try {
            Scanner reader = new Scanner(orderFile);

            for (int orderNumber = 0; orderNumber < getAmtOrders();)
            {
                //Create orderNumber amount of ShoppingCarts
                ShoppingCart s = new ShoppingCart();
                while (reader.hasNextLine()) {
                    data = reader.nextLine();

                    //Handle the first 4 lines. (0 OrderID, 1 StudentID, 2 OrderStatus, 3 PickupTime)
                    switch (positionInOrder) {
                        case 0:
                            break;
                        case 1:
                            s.setStudentID(data);
                            break;
                        case 2:
                            s.setStatus(data);
                            break;
                        case 3:
                            s.setPickupTime(data);
                            break;
                    }

                    //Handle subsequent lines until we find a '\'
                    if (!data.equals("\\") && positionInOrder != 0) {
                        //So we found


                    }

                    //END OF ORDER '\' found
                    if (data.equals("\\")) {
                        positionInOrder = 0;
                        break;
                    }


                    positionInOrder++;
                }
                //When we find a '\' increase orderNumber and set positionInOrder back to 0

            }



        } catch (FileNotFoundException e) {

        }
        for (int idx = 0; idx < getAmtOrders(); idx++) {
            //Each order starts with orderNumber
            //Each order ends with a backslash '\'
            try {
                Scanner reader = new Scanner(orderFile);
                ShoppingCart c1 = new ShoppingCart();
                while (reader.hasNextLine()) {

                    positionInOrder++;
                }
                reader.close();

            } catch (FileNotFoundException e) {

            }

        }
    }

    //Return how many orders the system currently has.
    public int getAmtOrders() {
        int amt = 0;
        String data = "";
        try {
            Scanner reader = new Scanner(orderFile);
            while (reader.hasNextLine()) {
                data += reader.nextLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(data);

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
            allOrders.add(s);
            FileWriter write = new FileWriter("orders.txt", true);
            write.write(s.fileString());
            write.write("\\\n");
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
