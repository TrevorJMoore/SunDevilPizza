package com.sundevilpizza.sundevilpizza;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class studentVerify {

	  @FXML
	    private Button button;

	    @FXML
	    private TextField username;

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
				System.out.println("success");
				System.out.println(id);
			}
			else {
				System.out.println("Incorrect User id");
			}
		//	name = (new StringBuilder()).append(typeUsername).append(name).toString();
			
	    }
			
}
	    
	  



