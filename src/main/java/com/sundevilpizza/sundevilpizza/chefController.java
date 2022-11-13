package com.sundevilpizza.sundevilpizza;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class chefController extends OrderPlacer {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

	@FXML
    private void startOrder(ActionEvent event) {
        event.consume();
        System.out.print("works");
        
    }
	@FXML
    private void cancelOrder(ActionEvent event) {
        event.consume();
        System.out.print("works2");
        
    }
	
}
