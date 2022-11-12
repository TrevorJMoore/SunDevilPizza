package com.sundevilpizza.sundevilpizza;

import java.util.ArrayList;

public class ShoppingCart {
    //Shopping Cart has Pizzas (multiple, in fact)
    private ArrayList<Pizza> cart = new ArrayList<>();


    //Accessors
    public double getTotal() {
        double total = 0;
        for (Pizza p : cart) {
            total += (p.getPizzaCost() + p.getToppingCost());
        }
        return total;
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
            ret += p.getPizzaSize() + " " + p.getPizzaType() + " - " + p.getToppings() + "\n";
        }
        return ret;
    }

}
