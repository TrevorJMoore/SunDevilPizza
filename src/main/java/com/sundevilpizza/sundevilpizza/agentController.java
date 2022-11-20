package com.sundevilpizza.sundevilpizza;

import javafx.fxml.Initializable;

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

public class agentController implements Initializable {

    public TextArea agentOrderText;
    OrderFileHandler o = new OrderFileHandler();

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        //Populate Agent View with Orders that have the status "ACCEPTED"
        if (url.toString().endsWith("agentView.fxml"))
        {
            o.populateOrders();
            populateAgentArea();
        }
    }

    private void populateAgentArea() {
        String agentText = "";
        for (int idx = 0; idx < o.getAmtOrders(); idx++)
        {
            if (o.getOrder(idx).getStatus().equals("ACCEPTED")) {
                agentText += o.getOrder(idx).employeeString();
                agentText += "~~~~~~~~~~~~~~~~~~~~~~~\n\n";
            }
        }
        agentOrderText.setText(agentText);
    }


    public void finalizeOrder(ActionEvent event) {
        //Get ready to copy to new OrderFileHandler
        OrderFileHandler newOrders = new OrderFileHandler();

        //We set the first order in the list displayed to "READY TO COOK" status
        int pos = 0;
        while (!o.getOrder(pos).getStatus().equals("ACCEPTED") && pos < o.getOrderSize()-1)
        {
            ++pos;
        }
        if (o.getOrder(pos).getStatus().equals("ACCEPTED")) {
            //We can cook this
            //Change the order we are operating on to "READY TO COOK" status
            o.getOrder(pos).setStatus("READY TO COOK");
            //Dump the text file and fill with newOrders' orders
            newOrders.addAllOrders(o);
        }

        populateAgentArea();
    }

    public void cancelOrder(ActionEvent event) {
        //Get ready to copy to new OrderFileHandler
        OrderFileHandler newOrders = new OrderFileHandler();

        //We set the first order in the list displayed to "READY TO COOK" status
        int pos = 0;
        while (!o.getOrder(pos).getStatus().equals("ACCEPTED") && pos < o.getOrderSize()-1)
        {
            ++pos;
        }
        if (o.getOrder(pos).getStatus().equals("ACCEPTED")) {
            //We can't cook this
            //Change the order we are operating on to "CANCELLED" status
            o.getOrder(pos).setStatus("CANCELLED");
            //Dump the text file and fill with newOrders' orders
            newOrders.addAllOrders(o);
        }

        populateAgentArea();
    }
}
