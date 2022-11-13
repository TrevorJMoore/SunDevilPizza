package com.sundevilpizza.sundevilpizza;

import java.util.ArrayList;

public class Order {
    private static ArrayList<ShoppingCart> orderCart = new ArrayList<>();


    //Copy our shopping cart into our order
    Order(ShoppingCart s) {
        orderCart.add(s);
    }


    public ShoppingCart getOrder(int idx) {
        return orderCart.get(idx);
    }

    public void addOrder(int idx, ShoppingCart s) {
        orderCart.add(idx,s);
    }

    public void addOrder(ShoppingCart s) {
        orderCart.add(s);
    }


}
