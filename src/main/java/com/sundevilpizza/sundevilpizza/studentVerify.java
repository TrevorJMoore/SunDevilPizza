package com.sundevilpizza.sundevilpizza;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class studentVerify {

	  @FXML
	    private Button button;

	    @FXML
	    private TextField username;

	    @FXML
	    private Label loginText;
	    
	    SunDevilPizzaApp s = new SunDevilPizzaApp();
	    
	    @FXML
	    void userLogin(ActionEvent event) {
	    	checkLogin();

	    }
	    private void checkLogin() {
	    	//
	    	
	    	// we check the length of asu id, assuming that the user has entered correct id
	    	// we dont have a databse to check it :(
	    	
	    	

		//	String typeUse = "Username: ";
			String id = username.getText().toString();
			if(id.length() == 10) {
				 loginText.setText("Success!");
		         
			}
			else {
				loginText.setText("Incorrect User id");
				
			}
		//	name = (new StringBuilder()).append(typeUsername).append(name).toString();
			
	    }
			
}
	    
	  



