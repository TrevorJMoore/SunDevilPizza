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
                populateOrders();
                for (ShoppingCart s : allOrders) {
                    System.out.println(s.fileString());
                }
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
        //System.out.println(data);

        for (int idx = 0; idx < data.length(); idx++) {
            if (data.charAt(idx) == '\\' ) {
                amt++;
            }
        }

        return amt;

    }

    public void setStatus(int idx, String status) {
        //Status is the third line down always
        try {

            Scanner reader = new Scanner(orderFile);
            StringBuilder sb = new StringBuilder();
            //String data = "";
            //Write as I read until we get there
            int totalLines = 0;
            System.out.println("Total Lines: " + totalLines);
            //So we know we have to read totalLines to get to order
            for (int i = 0; i < getAmtOrders()-idx; i++) {
                totalLines = amtLines(i);
                for (int line = 0; line < totalLines; line++) {
                    switch (line) {
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                            sb.append(reader.nextLine()).append("\n");
                            break;
                        default:
                            sb.append("\t").append(reader.nextLine());
                    }

                    System.out.println("Data: " + sb.toString());
                    //f1.write(data);

                }
                sb.append(reader.nextLine()).append("\n");
                totalLines = 0;
            }
            totalLines = amtLines(idx);
            for (int line = 0; line < totalLines; line++) {
                switch (line) {
                    case 0:
                    case 1:
                    case 3:
                        sb.append(reader.nextLine()).append("\n");
                        break;
                    case 2:
                        sb.append(status).append("\n");
                    default:
                        sb.append("\t").append(reader.nextLine());
                }
            }


            //We have arrived to the order
            //skip two lines then change status on third

            BufferedWriter bw = new BufferedWriter(new FileWriter(orderFile));
            bw.append(sb);
            bw.flush();

        } catch (IOException e) {

        }


    }

    /*public void removeOrder(int idx) {
        allOrders.remove(idx);
        //Apply changes to the file
        //First we find the location then we remove it
        try {
            Scanner reader = new Scanner(orderFile);
            Scanner copy = new Scanner(orderFile);
            String data = "";
            //Skip till we get to the idx entry
            for (int i = 0; i < idx; ) {
                data += reader.nextLine();
                copy.nextLine();
                if (data.equals("\\")) {
                    i++;
                }
            }
            //We have made it to the idx entry
            for (int i = 0; i < amtLines(idx); i++)
            {
                copy.nextLine();
            }
            //Copy holds what is after the idx entry
            while (reader.hasNextLine())
            {

            }


            FileWriter write = new FileWriter("orders.txt");

        } catch (Exception e) {

        }
    }*/

    public ShoppingCart getOrder(int idx) {
        return allOrders.get(idx);
    }

    public ArrayList<ShoppingCart> getOrders() {
        return allOrders;
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
