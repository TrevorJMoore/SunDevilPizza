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
        for (Node pizzaType: pizzaTypesBox.getChildren()) {
            if (pizzaType instanceof RadioButton && ((RadioButton)pizzaType).isSelected()) {
                switch (((RadioButton)pizzaType).getId()) {
                    case "origRadio":
                        p1.setPizzaType("Original Crust");
                        break;
                    case "thinRadio":
                        p1.setPizzaType("Thin Crust");
                        break;
                    case "chickpeaRadio":
                        p1.setPizzaType("Chickpea Crust");
                        break;
                    case "cauliflowerRadio":
                        p1.setPizzaType("Cauliflower Crust");
                        break;
                }
                switch (((RadioButton)pizzaType).getId()) {
                    case "mediumRadio":
                        p1.setPizzaSize("Medium");
                        break;
                    case "largeRadio":
                        p1.setPizzaSize("Large");
                        break;
                    case "smallRadio":
                        p1.setPizzaSize("Small");
                        break;
                }

            }
        }

        //Pizza Toppings
        ArrayList<String> top = new ArrayList<>();
        for (Node n1: pizzaToppingsBox.getChildren()) {
            if (n1 instanceof CheckBox && ((CheckBox)n1).isSelected()) {
                switch (((CheckBox)n1).getId()) {
                    case "xtraCheckBox":
                        top.add("Xtra-Cheese");
                        break;
                    case "pepperoniCheckBox":
                        top.add("Pepperoni");
                        break;
                    case "sausageCheckBox":
                        top.add("Sausage");
                        break;
                    case "hamCheckBox":
                        top.add("Ham");
                        break;
                    case "jalapenoCheckBox":
                        top.add("Jalapenos");
                        break;
                    case "oliveCheckBox":
                        top.add("Olives");
                        break;
                    case "onionCheckBox":
                        top.add("Onions");
                        break;
                    case "mushroomCheckBox":
                        top.add("Mushrooms");
                        break;
                    case "pineappleCheckBox":
                        top.add("Pineapples");
                        break;
                }
            }
        }
        p1.addToppings(top);


        //Pizza Price
        p1.setPizzaCost(calculateCost());
        return p1;
    }

    //REMOVE IN PROD: Change hardcoded numbers to be reflected in the class they are representing.
    private double calculateCost() {
        pizzaCost = 0;
        for (Node pizzaSize: pizzaTypesBox.getChildren()) {
            if (pizzaSize instanceof RadioButton && ((RadioButton)pizzaSize).isSelected()){
                switch (((RadioButton) pizzaSize).getId()) {
                    case "mediumRadio":
                        pizzaCost += 8.00;
                        break;
                    case "largeRadio":
                        pizzaCost += 10.00;
                        break;
                    case "smallRadio":
                        pizzaCost += 6.00;
                        break;
                }
            }
        }
        for (Node n1: pizzaToppingsBox.getChildren()) {
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

    @FXML
    private void removeFromCart(ActionEvent event) {
        if (s1.getLength() < 0) {
            return;
        }
        s1.removePizza(s1.getLength());
        textArea.setText(s1.toString());

        //Change from text area to maybe a comboBox to select which order to delete?

        cartTotalLabel.setText(String.format("Cart Total: $%,.2f", s1.getTotal()));

        event.consume();
    }


<<<<<<< Updated upstream
=======
                    OrderFileHandler o = new OrderFileHandler();
                    s1.setOrderID( Integer.toString(o.getAmtOrders()) );
                    s1.setStudentID(asuID);
                    s1.setStatus("ACCEPTED");
                    o.addOrder(s1);

                    Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                    a.setTitle("Order Placed!");
                    a.setContentText("Order has been placed!\n"+"Email sent to ASUID: " + asuID);
                    a.show();
                }
            }
        };

        event.consume();
        Alert a = new Alert(Alert.AlertType.ERROR);
        if (textArea.getLength() <= 0) {
            a.setTitle("Order Not Placed");
            a.setContentText("ERROR - Did not place an order!");
            a.show();
            addButton.requestFocus();
        } else if (pickupCombo.getValue() == null)
        {
            a.setTitle("Pickup Not Selected");
            a.setContentText("ERROR - Did not select a pickup time!");
            a.show();
            pickupCombo.requestFocus();
        } else {
            try {
                s1.setPickupTime((String) pickupCombo.getValue());
                Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
                Scene checkoutScene = new Scene(root);
                Stage checkoutStage = placeOrderStage();
                checkoutStage.setScene(checkoutScene);
                checkoutStage.show();
                checkoutStage.setOnHiding(verifyClose);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
       	
       }


    @FXML
    void userLogin(ActionEvent event) {
        checkLogin();
        if (verifiedLogin)
        {
            Stage verifyStage = (Stage) verifyButton.getScene().getWindow();
            verifyStage.close();
        }
    }
    private void checkLogin() {
        //

        // we check the length of asu id, assuming that the user has entered correct id
        // we dont have a databse to check it :(



        //	String typeUse = "Username: ";
        try {
            String id = username.getText().toString();
            if (id.length() == 10 && Integer.parseInt(id) > 1000000000) {
                loginText.setText("Success!");

                asuID=id;
                verifiedLogin = true;
                /*a.setTitle("Order Placed!");
                a.setContentText("Order has been placed!\n"+"Email sent to ASUID: " + asuID);
                a.show();*/


                System.out.println("Order placed");

            } else {
                loginText.setText("Incorrect User id");
            }
        } catch (NumberFormatException e) {
            loginText.setText("Please enter a valid numerical ID.");
        }
        //	name = (new StringBuilder()).append(typeUsername).append(name).toString();

    }
>>>>>>> Stashed changes

}
