package com.sundevilpizza.sundevilpizza;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.ArrayList;

public class SunDevilPizzaHome extends Application {


    public TextField employeeID;


	    @FXML
	    void userLogin(ActionEvent event) {
	    	
	    	try {
                int id = Integer.parseInt(employeeID.getText());
                if (id == 9999) {
                    Parent root = FXMLLoader.load(getClass().getResource("agentView.fxml"));
                    Scene agentScene = new Scene(root);
                    Stage agentStage = agentViewStage();
                    agentStage.setScene(agentScene);
                    agentStage.show();
                }
                else if (id == 1111) {
                    Parent root = FXMLLoader.load(getClass().getResource("chefView.fxml"));
                    Scene chefScene = new Scene(root);
                    Stage chefStage = chefViewStage();
                    chefStage.setScene(chefScene);
                    chefStage.show();
                }
                else {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("Invalid Credentials");
                    a.setContentText("ERROR - Invalid Employee ID.");
                    a.show();
                }

               // chefStage.setOnCloseRequest(closeOrder);
            } catch (Exception e) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Invalid Credentials");
                a.setContentText("ERROR - Invalid Employee ID.");
                a.show();
            }
	    	

	    }


    private final int WIDTH = 500, HEIGHT = 500;

    Font headerFont = new Font("Arial", 48);
    Font bodyFont = new Font("Arial", 24);
    Font lesserFont = new Font("Arial", 16);


    private Stage placeOrderStage() {
        Stage placeOrderStage = new Stage();
        placeOrderStage.setTitle("Sun Devil Pizza - Place Order");
        return placeOrderStage;
    }

    private Stage viewOrderStage() {
        Stage placeOrderStage = new Stage();
        placeOrderStage.setTitle("Sun Devil Pizza - View Order");
        return placeOrderStage;
    }

    private Stage loginStage() {
        Stage placeOrderStage = new Stage();
        placeOrderStage.setTitle("Sun Devil Pizza - Login");
        return placeOrderStage;
    }

    private Stage chefViewStage() {
        Stage placeOrderStage = new Stage();
        placeOrderStage.setTitle("Sun Devil Pizza - Chef");
        return placeOrderStage;
    }

    private Stage agentViewStage() {
        Stage placeOrderStage = new Stage();
        placeOrderStage.setTitle("Sun Devil Pizza - Processing Agent");
        return placeOrderStage;
    }


    @Override
    public void start(Stage mainStage) throws Exception {
        //Main Window Customizing
        mainStage.setTitle("Sun Devil Pizza - Home");
        mainStage.setResizable(false);

        //Initialize Header, PlaceOrder Button, ViewOrder Button, and Login Button
        Label titleHeader = new Label();
        titleHeader.setText("Sun Devil Pizza");
        titleHeader.setFont(headerFont);

        Button placeOrderButton = new Button();
        placeOrderButton.setText("Place an Order");
        placeOrderButton.setFont(bodyFont);
        placeOrderButton.setStyle("-fx-background-color: #ffc900; ");

        Button viewOrderButton = new Button();
        viewOrderButton.setText("View an Order");
        viewOrderButton.setFont(bodyFont);
        viewOrderButton.setStyle("-fx-background-color: #ffc900; ");

        Button loginButton = new Button();
        loginButton.setText("Restaurant Login");
        loginButton.setFont(lesserFont);
        loginButton.setStyle("-fx-background-color: #ffc900; ");

        //Begin by loading the default / welcome view Scene
        VBox layout = new VBox();
        layout.setStyle("-fx-background-color: white; ");
        layout.getChildren().addAll(titleHeader, placeOrderButton, viewOrderButton, loginButton);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(16);


        Scene homeScene = new Scene(layout, WIDTH, HEIGHT);
        mainStage.setScene(homeScene);
        mainStage.show();


        //Event Handling for Main Buttons (Place Order / View Order / Login Buttons) and Form Closing
        EventHandler<WindowEvent> closeOrder = new EventHandler<>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                mainStage.show();
            }
        };

        EventHandler<ActionEvent> placeOrder = actionEvent -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("placeOrder.fxml"));
                Scene orderScene = new Scene(root);
                Stage orderStage = placeOrderStage();

                orderStage.setScene(orderScene);
                orderStage.show();
                orderStage.setOnHidden(closeOrder);
            } catch (IOException e) {
                e.printStackTrace();
            }
            mainStage.hide();
        };

        EventHandler<ActionEvent> viewOrder = actionEvent -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("viewOrder.fxml"));
                Scene orderScene = new Scene(root);
                Stage orderStage = viewOrderStage();

                orderStage.setScene(orderScene);
                orderStage.show();
                orderStage.setOnCloseRequest(closeOrder);
            } catch (IOException e) {
                e.printStackTrace();
            }

            mainStage.hide();
        };
        

        EventHandler<ActionEvent> login = actionEvent -> {
        	 try {
                 Parent root = FXMLLoader.load(getClass().getResource("login2.fxml"));
                 Scene chefScene = new Scene(root);
                 Stage chefStage = loginStage();

                 chefStage.setScene(chefScene);
                 chefStage.show();
                 chefStage.setOnCloseRequest(closeOrder);
             } catch (IOException e) {
                 e.printStackTrace();
             }
             mainStage.hide();

        };


        placeOrderButton.setOnAction(placeOrder);
        viewOrderButton.setOnAction(viewOrder);
        loginButton.setOnAction(login);

    }

    public static void main(String[] args) {
        launch();
    }

}
