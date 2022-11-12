module com.sundevilpizza.sundevilpizza {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
                        
    opens com.sundevilpizza.sundevilpizza to javafx.fxml;
    exports com.sundevilpizza.sundevilpizza;
}