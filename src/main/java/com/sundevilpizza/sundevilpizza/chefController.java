package com.sundevilpizza.sundevilpizza;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class chefController implements Initializable {


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


	OrderFileHandler o = new OrderFileHandler();

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		//Populate Chef View with Orders that have the status "Ready to Cook" and "Cooking"
		if (url.toString().endsWith("chefView.fxml"))
		{
			o.populateOrders();
			populateChefArea();
		}

	}

	private void populateChefArea() {
		String chefText = "";
		for (int idx = 0; idx < o.getAmtOrders(); idx++)
		{
			if (o.getOrder(idx).getStatus().equals("READY TO COOK") || o.getOrder(idx).getStatus().equals("COOKING")) {
				chefText += o.getOrder(idx).employeeString();
				chefText += "~~~~~~~~~~~~~~~~~~~~~~~\n\n";
			}
		}
		chefOrderText.setText(chefText);
	}

	@FXML
    private void startOrder(ActionEvent event) {
        event.consume();

	
        //Get ready to copy to new OrderFileHandler
		OrderFileHandler newOrders = new OrderFileHandler();

		//We set the first order in the list displayed to "COOKING" status
		int pos = 0;
		while ((!o.getOrder(pos).getStatus().equals("READY TO COOK") && !o.getOrder(pos).getStatus().equals("COOKING")) && pos < o.getOrderSize()-1)
		{
			++pos;
		}
		if (o.getOrder(pos).getStatus().equals("READY TO COOK")) {
			//We can cook this
			//Change the order we are operating on to "COOKING" status
			o.getOrder(pos).setStatus("COOKING");
			//Dump the text file and fill with newOrders' orders
			newOrders.addAllOrders(o);
		} else if (o.getOrder(pos).getStatus().equals("COOKING")) {
			//It is already cooking...
			Alert a = new Alert(Alert.AlertType.ERROR);
			a.setTitle("Previous Order Cooking");
			a.setContentText("ERROR - Must finish cooking previous order before starting a new order.");
			a.show();
		}

		populateChefArea();

        
    }
	@FXML
    private void finishOrder(ActionEvent event) {
        event.consume();

		//Get ready to copy to new OrderFileHandler
		OrderFileHandler newOrders = new OrderFileHandler();

		//We set the first order in the list displayed to "COOKING" status
		int pos = 0;
		while ((!o.getOrder(pos).getStatus().equals("READY TO COOK") && !o.getOrder(pos).getStatus().equals("COOKING")) && pos < o.getOrderSize()-1)
		{
			pos++;
		}
		if (o.getOrder(pos).getStatus().equals("READY TO COOK")) {
			//We can't finalize this
			//ADD ERROR ALERT ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			Alert a = new Alert(Alert.AlertType.ERROR);
			a.setTitle("No Order Cooking");
			a.setContentText("ERROR - Must start cooking to finalize the order.");
			a.show();
		} else if (o.getOrder(pos).getStatus().equals("COOKING")) {
			//Finalize the order as Ready for Pickup
			o.getOrder(pos).setStatus("READY FOR PICKUP");
			//Dump the text file and fill with newOrders' orders
			newOrders.addAllOrders(o);
		}
		populateChefArea();
        
    }

	public void cancelOrder(ActionEvent event) {
		event.consume();

		//Get ready to copy to new OrderFileHandler
		OrderFileHandler newOrders = new OrderFileHandler();

		//We set the first order in the list displayed to "COOKING" status
		int pos = 0;
		while ((!o.getOrder(pos).getStatus().equals("READY TO COOK") && !o.getOrder(pos).getStatus().equals("COOKING")) && pos < o.getOrderSize()-1)
		{
			pos++;
		}
		if (o.getOrder(pos).getStatus().equals("READY TO COOK")) {
			o.getOrder(pos).setStatus("CANCELLED");
			newOrders.addAllOrders(o);
			System.out.println("Cancelled READY TO COOK order.");
		} else if (o.getOrder(pos).getStatus().equals("COOKING")) {
			o.getOrder(pos).setStatus("CANCELLED");
			//Dump the text file and fill with newOrders' orders
			newOrders.addAllOrders(o);
			System.out.println("Cancelled COOKING order.");
		}
		populateChefArea();

	}
}
