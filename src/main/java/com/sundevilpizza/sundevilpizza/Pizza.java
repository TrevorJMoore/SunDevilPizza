package com.sundevilpizza.sundevilpizza;

import java.util.ArrayList;

public class Pizza {
    //A pizza has toppings, pizza type, and a cost
    private ArrayList<String> m_toppings = new ArrayList<>();
    private double m_toppingCost = 0, m_pizzaCost = 0;
    private String m_pizzaType;
    private String m_pizzaSize;

    //Accessor and Mutator methods
    public void setPizzaType(String pizzaType) {
        m_pizzaType = pizzaType;
    }

    public void setPizzaSize(String pizzaSize) {
        m_pizzaSize = pizzaSize;
    }

    public void setToppingCost(double cost) {
        m_toppingCost = cost;
    }

    public void setPizzaCost(double cost) {
        m_pizzaCost = cost;
    }



    public String getToppings() {
        String ret = "";
        for (String top: m_toppings) {
            ret += top + ", ";
        }
        try {
            ret = ret.substring(0, ret.length() - 2);
        } catch (StringIndexOutOfBoundsException ex) {
            ret = ret;
        }
        return ret;
    }

    public String getPizzaType() {
        return m_pizzaType;
    }

    public String getPizzaSize() {
        return m_pizzaSize;
    }

    public double getToppingCost() {
        return m_toppingCost;
    }

    public double getPizzaCost() {
        return m_pizzaCost;
    }


    //General Use Methods
    public void addTopping(String topping) {
        m_toppings.add(topping);
    }

    public void addToppings(ArrayList<String> toppings) {
        for (String topping : toppings) {
            m_toppings.add(topping);
        }
    }

    public void removeTopping(String topping) {
        if (m_toppings.contains(topping))
            m_toppings.remove(topping);
        else
            System.out.println("Could not find topping in order.");
    }



}
