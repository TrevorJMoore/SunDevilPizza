//This is an observer design class it observes updates on the OrderPlace view.

package com.sundevilpizza.sundevilpizza;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.*;


public class OrderPlacer implements Initializable {

    public TextArea textArea;
    public Label cartTotalLabel;
    public Label currentTotalLabel;

    public Button addButton;
    public Button removeButton;
    public Button placeOrderButton;

    public VBox pizzaTypesBox;
    public VBox pizzaToppingsBox;

    public ComboBox pickupCombo;

    public TextField username;
    public Button verifyButton;
    public Label loginText;


    double pizzaCost = 0;
    ShoppingCart s1 = new ShoppingCart();
    static boolean verifiedLogin = false;
    static String asuID;

    public void fillHours(ComboBox b) {
        //Obtain the current hour
        int hour = Integer.parseInt(LocalTime.now().toString().substring(0,2));
        //If the current minutes are 15 minutes from the hour, increase our hour
        //It doesn't make sense to place an order for pickup in 5 minutes.
        if (Integer.parseInt(LocalTime.now().toString().substring(3,5)) > 45) {
            hour++;
        }


        ArrayList<String> times = new ArrayList<>();
        //5am to 10pm pickup times
        for (int idx = hour; idx < 23; idx++) {
            if (idx < 11) {
                times.add(idx+":00am - " + (idx+1) + ":00am");
            }
            else if (idx == 11) {
                times.add(idx+":00am - " + (idx+1) + ":00pm");
            }
            else if (idx == 22) {
                times.add((idx-11)+":00pm - " + (idx-10)+":00am");
            }
            else
                times.add((idx-11)+":00pm - " + (idx-10) + ":00pm");
        }

        b.setItems(FXCollections.observableArrayList(times));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (url.toString().endsWith("placeOrder.fxml"))
        {
            fillHours(pickupCombo);
        }

    }


    //Obtain all pizzaInfo such as Type, Size, Cost
    public Pizza pizzaInfo() {
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

    private Stage placeOrderStage() {
        Stage placeOrderStage = new Stage();
        placeOrderStage.setTitle("Sun Devil Pizza - Place Order");
        return placeOrderStage;
    }



    @FXML
    void placeOrder(ActionEvent event) {

        EventHandler<WindowEvent> verifyClose = new EventHandler<>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                if (verifiedLogin)
                {
                    Stage placeOrderStage = (Stage) addButton.getScene().getWindow();
                    placeOrderStage.close();

                    //Write the order down

                    OrderFileHandler o = new OrderFileHandler();
                    o.populateOrders();
                    int orderId = Integer.parseInt(o.getLastOrderID())+1;
                    s1.setOrderID( Integer.toString(orderId) );
                    s1.setStudentID(asuID);
                    s1.setStatus("ACCEPTED");
                    o.addOrder(s1);
                    System.out.println("AmtLines: " + o.amtLines(orderId));

                    Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                    a.setTitle("Order Placed!");
                    a.setContentText("Order has been placed!\n"+"Your OrderID is: " + orderId + "\nEmail sent to ASUID: " + asuID);
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

}
