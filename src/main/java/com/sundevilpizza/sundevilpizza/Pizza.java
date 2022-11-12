package com.sundevilpizza.sundevilpizza;

import java.util.ArrayList;

public class Pizza {
    //A pizza has toppings, pizza type, and a cost
    private ArrayList<String> m_toppings = new ArrayList<>();
    private double m_toppingCost = 0;
    private String m_pizzaType;

    //Accessor and Mutator methods
    public void setPizzaType(String pizzaType) {
        m_pizzaType = pizzaType;
    }

    public void setToppingCost(double cost) {
        m_toppingCost = cost;
    }

    public ArrayList<String> getToppings() {
        return m_toppings;
    }

    public String getPizzaType() {
        return m_pizzaType;
    }

    public double getToppingCost() {
        return m_toppingCost;
    }

    //General Use Methods
    public double getPizzaCost() {
        double pizzaCost = 0;
        for (String topping : m_toppings) {
            pizzaCost += m_toppingCost;
        }
        return pizzaCost;
    }

    public void addTopping(String topping) {
        m_toppings.add(topping);
    }

    public void removeTopping(String topping) {
        if (m_toppings.contains(topping))
            m_toppings.remove(topping);
        else
            System.out.println("Could not find topping in order.");
    }



}
