package com.sundevilpizza.sundevilpizza;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class OrderPlacer {


    public RadioButton origRadio;
    public RadioButton thinRadio;
    public RadioButton chickpeaRadio;
    public RadioButton cauliflowerRadio;
    public ToggleGroup PizzaTypeToggle;

    public RadioButton smallRadio;
    public RadioButton mediumRadio;
    public RadioButton largeRadio;
    public ToggleGroup PizzaSizeToggle;

    public CheckBox xtraCheckBox;
    public CheckBox pepperoniCheckBox;
    public CheckBox sausageCheckBox;
    public CheckBox hamCheckBox;
    public CheckBox jalapenoCheckBox;
    public CheckBox oliveCheckBox;
    public CheckBox onionCheckBox;
    public CheckBox mushroomCheckBox;
    public CheckBox pineappleCheckBox;

    public ScrollPane scrollPane;
    public Label cartTotalLabel;

    public Button addButton;
    public Button removeButton;
    public Button placeOrderButton;

    public VBox pizzaTypesBox;
    public VBox pizzaToppingsBox;
    public VBox shoppingCartBox;
    public HBox sizeBox;


    double pizzaCost = 0;


    @FXML
    private void calculateTotal(ActionEvent event) {
        pizzaCost = 0;
        event.consume();
        for (Node pizzaSize : pizzaTypesBox.getChildren()) {
            if (pizzaSize instanceof RadioButton && ((RadioButton)pizzaSize).isSelected()){
                switch (((RadioButton)pizzaSize).getId())
                {
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
        for (Node n1 : pizzaToppingsBox.getChildren()) {
            if (n1 instanceof CheckBox) {
                if (((CheckBox)n1).isSelected())
                    pizzaCost+=0.25;
            }
        }
        System.out.println(pizzaCost);

    }

}
