//This is an observer design class it observes updates on the OrderView view.
package com.sundevilpizza.sundevilpizza;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Objects;

public class OrderViewer {
    public TextField orderIDTextField;
    public TextField studentIDTextField;
    public TextArea viewTextArea;

    @FXML
    public void searchOrder(ActionEvent actionEvent) {
        String orderID = orderIDTextField.getText();
        String studentID = studentIDTextField.getText();

        //Search for the order specified
        OrderFileHandler o = new OrderFileHandler();
        //Grab all data from text file
        o.populateOrders();

        //Check each order and see if it has matching orderID and studentID
        for (int idx = 0; idx < o.getAmtOrders(); idx++) {
            if (Objects.equals(o.getOrder(idx).getOrderID(), orderID) && Objects.equals(o.getOrder(idx).getStudentID(), studentID))
            {
                viewTextArea.setText(o.getOrder(idx).viewString());

            }
            else {
            	viewTextArea.setText("Please check the entered OrderID and/or ASU ID is correct");
            }
        }


    }


}
