module org.example.rideshareapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.rideshareapp to javafx.fxml;
    exports org.example.rideshareapp;
}