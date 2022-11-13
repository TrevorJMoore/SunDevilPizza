package com.sundevilpizza.sundevilpizza;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class chefController extends OrderPlacer {
	
	// public TextArea chefOrderText;
	
	//ShoppingCart s2 = new ShoppingCart();
	//Pizza s;
	
	
	@FXML
    private void startOrder(ActionEvent event) {
        event.consume();
     //  this.s = s;
       // s.getPizzaType();
      //  chefOrderText.setText(s.getPizzaSize());
        
        // gets order data
        
        System.out.print("works");
        
    }
	@FXML
    private void cancelOrder(ActionEvent event) {
        event.consume();
        System.out.print("works2");
        
    }
	
}
