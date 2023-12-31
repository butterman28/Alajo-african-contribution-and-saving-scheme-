module com.example.alajo {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.csv;
    requires com.opencsv;


    opens com.example.alajo to javafx.fxml;
    exports com.example.alajo;
    exports com.example.alajo.model;
    opens com.example.alajo.model to javafx.fxml;
}