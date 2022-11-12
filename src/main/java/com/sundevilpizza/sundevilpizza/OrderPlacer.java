//This is an observer design class it observes updates on the OrderPlace view.

package com.sundevilpizza.sundevilpizza;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReferenceArray;


public class OrderPlacer {


    public RadioButton origRadio;
    public RadioButton thinRadio;
    public RadioButton chickpeaRadio;
    public RadioButton cauliflowerRadio;
    public ToggleGroup PizzaTypeToggle;

    public RadioButton smallRadio;
    public RadioButton mediumRadio;
    public RadioButton largeRadio;
    public ToggleGroup PizzaSizeToggle;

    public CheckBox xtraCheckBox;
    public CheckBox pepperoniCheckBox;
    public CheckBox sausageCheckBox;
    public CheckBox hamCheckBox;
    public CheckBox jalapenoCheckBox;
    public CheckBox oliveCheckBox;
    public CheckBox onionCheckBox;
    public CheckBox mushroomCheckBox;
    public CheckBox pineappleCheckBox;

    public TextArea textArea;
    public Label cartTotalLabel;
    public Label currentTotalLabel;

    public Button addButton;
    public Button removeButton;
    public Button placeOrderButton;

    public VBox pizzaTypesBox;
    public VBox pizzaToppingsBox;
    public VBox shoppingCartBox;
    public HBox sizeBox;



    double pizzaCost = 0;
    ShoppingCart s1 = new ShoppingCart();

    //Obtain all pizzaInfo such as Type, Size, Cost
    private Pizza pizzaInfo() {
        Pizza p1 = new Pizza();
        //Check Pizza Type and Size
        for (Node pizzaType : pizzaTypesBox.getChildren()) {
            if (pizzaType instanceof RadioButton && ((RadioButton)pizzaType).isSelected()) {
                switch (((RadioButton)pizzaType).getId()) {
                    case "origRadio" -> p1.setPizzaType("Original Crust");
                    case "thinRadio" -> p1.setPizzaType("Thin Crust");
                    case "chickpeaRadio" -> p1.setPizzaType("Chickpea Crust");
                    case "cauliflowerRadio" -> p1.setPizzaType("Cauliflower Crust");
                }
                switch (((RadioButton)pizzaType).getId()) {
                    case "mediumRadio" -> p1.setPizzaSize("Medium");
                    case "largeRadio" -> p1.setPizzaSize("Large");
                    case "smallRadio" -> p1.setPizzaSize("Small");
                }

            }
        }

        //Pizza Toppings
        ArrayList<String> top = new ArrayList<>();
        for (Node n1 : pizzaToppingsBox.getChildren()) {
            if (n1 instanceof CheckBox && ((CheckBox)n1).isSelected()) {
                switch (((CheckBox)n1).getId()) {
                    case "xtraCheckBox" -> top.add("Xtra-Cheese");
                    case "pepperoniCheckBox" -> top.add("Pepperoni");
                    case "sausageCheckBox" -> top.add("Sausage");
                    case "hamCheckBox" -> top.add("Ham");
                    case "jalapenoCheckBox" -> top.add("Jalapenos");
                    case "oliveCheckBox" -> top.add("Olives");
                    case "onionCheckBox" -> top.add("Onions");
                    case "mushroomCheckBox" -> top.add("Mushrooms");
                    case "pineappleCheckBox" -> top.add("Pineapples");
                }
            }
        }
        p1.addToppings(top);


        //Pizza Price
        p1.setPizzaCost(calculateCost());
        return p1;
    }

    //REMOVE IN PROD -> Change hardcoded numbers to be reflected in the class they are representing.
    private double calculateCost() {
        pizzaCost = 0;
        for (Node pizzaSize : pizzaTypesBox.getChildren()) {
            if (pizzaSize instanceof RadioButton && ((RadioButton)pizzaSize).isSelected()){
                switch (((RadioButton) pizzaSize).getId()) {
                    case "mediumRadio" -> pizzaCost += 8.00;
                    case "largeRadio" -> pizzaCost += 10.00;
                    case "smallRadio" -> pizzaCost += 6.00;
                }
            }
        }
        for (Node n1 : pizzaToppingsBox.getChildren()) {
            if (n1 instanceof CheckBox) {
                if (((CheckBox)n1).isSelected())
                    pizzaCost+=0.25;
            }
        }
        return pizzaCost;
    }

    @FXML
    private void calculateTotal(ActionEvent event) {
        event.consume();
        currentTotalLabel.setText(String.format("Current Total: $%,.2f", calculateCost()));
    }

    @FXML
    private void addToCart(ActionEvent event) {
        s1.addPizza(pizzaInfo());
        textArea.setText(s1.toString());

        cartTotalLabel.setText(String.format("Cart Total: $%,.2f", s1.getTotal()));

        event.consume();
    }



}
