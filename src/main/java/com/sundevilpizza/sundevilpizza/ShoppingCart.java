package com.sundevilpizza.sundevilpizza;

import java.util.ArrayList;

public class ShoppingCart {
    //Shopping Cart has Pizzas (multiple, in fact)
    private ArrayList<Pizza> cart = new ArrayList<>();
    private String pickupTime, orderID, studentID, orderStatus;

    public void setPickupTime(String pickup) {
        pickupTime = pickup;
    }

    public void setOrderID(String order) {
        orderID = order;
    }

    public void setStudentID(String student) {
        studentID = student;
    }

    public void setStatus(String status) {
        orderStatus = status;
    }

    //Accessors
    public String getStatus() {
        return orderStatus;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getStudentID() {
        return studentID;
    }

    public double getTotal() {
        double total = 0;
        for (Pizza p : cart) {
            total += (p.getPizzaCost() + p.getToppingCost());
        }
        return total;
    }

    public ArrayList<Pizza> getPizzas() {
        return cart;
    }

    public int getLength() {
        return cart.size()-1;
    }

    public Pizza getPizza(int index) {
        return cart.get(index);
    }


    public void addPizza(Pizza p) {
        cart.add(p);
    }

    public void addPizza(Pizza p, int index) {
        cart.add(index, p);
    }

    //Remove a specified pizza
    public void removePizza(Pizza p) {
        cart.remove(cart.lastIndexOf(p));
    }

    public void removePizza(int idx) {
        cart.remove(idx);
    }

    //Override toString
    @Override
    public String toString() {
        String ret = "";
        //Ex: Small Original Crust - Pepperoni, Cheese
        //Ex: Size Type - Toppings
        for (Pizza p: cart) {
            if (p.hasToppings()) {
                ret += p.getPizzaSize() + " " + p.getPizzaType() + " - " + p.getToppings() + "\n";
            }
            else {
                ret += p.getPizzaSize() + " " + p.getPizzaType() + p.getToppings() + "\n";
            }
        }
        return ret;
    }

    public String fileString() {
        String ret = "";
        //Ex: Small Original Crust - Pepperoni, Cheese
        //Ex: Size Type - Toppings
        ret += orderID + "\n";
        ret += studentID + "\n";
        ret += orderStatus + "\n";
        ret += pickupTime + "\n";

        for (Pizza p: cart) {
            if (p.hasToppings()) {
                ret += "\t" + p.getPizzaSize() + " " + p.getPizzaType() + " - " + p.getToppings() + "\n";
            }
            else {
                ret += "\t" + p.getPizzaSize() + " " + p.getPizzaType() + p.getToppings() + "\n";
            }
        }
        return ret;
    }

}
