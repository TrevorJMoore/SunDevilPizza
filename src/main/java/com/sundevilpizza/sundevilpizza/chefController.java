package com.sundevilpizza.sundevilpizza;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class chefController extends OrderFileHandler {


//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//
//    }
	
	 @FXML
	    private Button cancelOrderButton;

	    @FXML
	    private TextArea chefOrderText;

	    @FXML
	    private Button startOrderButton;

	@FXML
    private void startOrder(ActionEvent event) {
        event.consume();
        
        int amt = 0;
        String data = "";
        try {
        	
        	 data = new String(Files.readAllBytes(Paths.get("orders.txt")));
        	 } catch (IOException e) {
        		 e.printStackTrace(); 
        		 } 
        	System.out.println("Text file as String in Java"); 
        	System.out.println(data);

        	chefOrderText.setText(data);
        	
	
        
        OrderFileHandler f = new OrderFileHandler();
        f.getOrder(amt).setStatus("Ready to Cook");
        
        
        System.out.print("works");
        
    }
	@FXML
    private void cancelOrder(ActionEvent event) {
        event.consume();
        chefOrderText.setText(null);
        System.out.print("works2");
        
    }
	
}
