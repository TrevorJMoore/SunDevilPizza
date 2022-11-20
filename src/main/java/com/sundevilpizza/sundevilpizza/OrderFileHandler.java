package com.sundevilpizza.sundevilpizza;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class OrderFileHandler {

    private static File orderFile;
    private ArrayList<ShoppingCart> allOrders = new ArrayList<>();

    //Create the order file if not already created.
    public OrderFileHandler() {
        try {
            orderFile = new File("orders.txt");
            if (orderFile.createNewFile()) {
                System.out.println("File created: " + orderFile.getName());

            } else {
                System.out.println(orderFile.getName() + " already exists.");
                //Obtain all cached orders and fill them
                //populateOrders();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ShoppingCart> getAllOrders() {
        return allOrders;
    }

    public void populateOrders() {
        String data = "";
        int positionInOrder = 0;
        try {
            Scanner reader = new Scanner(orderFile);
            System.out.println(getAmtOrders());
            for (int orderNumber = 0; orderNumber < getAmtOrders(); orderNumber++)
            {
                //Create orderNumber amount of ShoppingCarts
                ShoppingCart s = new ShoppingCart();
                //s.setOrderID(Integer.toString(orderNumber));
                System.out.println("Order Number: " + orderNumber);
                while (reader.hasNextLine()) {
                    data = reader.nextLine();

                    //Handle the first 4 lines. (0 OrderID, 1 StudentID, 2 OrderStatus, 3 PickupTime)
                    //Handle subsequent lines until we find a '\'
                    if (!data.equals("\\")) {
                        switch (positionInOrder) {
                            case 0:
                                s.setOrderID(data);
                            case 1:
                                s.setStudentID(data);
                                break;
                            case 2:
                                s.setStatus(data);
                                break;
                            case 3:
                                s.setPickupTime(data);
                                break;
                            default:
                                //So we found pizza info
                                data = data.replaceAll("\t", "");
                                String[] split = data.replaceAll(",", "").split(" ");
                                //We know the first 3 | Size | Crust Crust
                                Pizza p = new Pizza();
                                p.setPizzaSize(split[0]);
                                p.setPizzaType(split[1] + " " + split[2]);
                                for (int j = 4; j < split.length; j++) {
                                    p.addTopping(split[j]);
                                    System.out.println(split[j]);
                                }
                                s.addPizza(p);
                        }




                    }
                    positionInOrder++;

                    //When we find a '\' set positionInOrder back to 0
                    //END OF ORDER '\' found
                    if (data.equals("\\")) {
                        positionInOrder = 0;
                        break;
                    }

                }
                allOrders.add(s);

            }



        } catch (FileNotFoundException e) {

        }
    }

    public String getLastOrderID() {
        if (allOrders.size() > 0) {
            return allOrders.get(allOrders.size() - 1).getOrderID();
        }
        else
            return "-1";
    }

    public int amtLines(int idx) {
        String[] s1 = allOrders.get(idx).fileString().split("\n");
        return s1.length;
    }

    public int getOrderSize() {
        return allOrders.size();
    }

    //Return how many orders the system currently has. 1-based, not 0-based.
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
        //System.out.println(data);

        for (int idx = 0; idx < data.length(); idx++) {
            if (data.charAt(idx) == '\\' ) {
                amt++;
            }
        }

        return amt;

    }

    public ShoppingCart getOrder(int idx) {
        return allOrders.get(idx);
    }

    public ArrayList<ShoppingCart> getOrders() {
        return allOrders;
    }

    public void addAllOrders(OrderFileHandler o)
    {

        for (int idx = 0; idx < o.getOrderSize(); idx++)
        {
            this.addOrder(o.getOrder(idx));
        }

        try {
            //Dump contents of file
            FileWriter write = new FileWriter("orders.txt");
            write.write("");
            write.close();
            FileWriter appendWrite = new FileWriter("orders.txt", true);
            for (ShoppingCart s : o.getAllOrders())
            {
                appendWrite.write(s.fileString());
                appendWrite.write("\\\n");
            }
            appendWrite.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


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
