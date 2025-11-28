module org.example.rideshareapp {
    // JavaFX
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    // JDBC (for DB access)
    requires java.sql;

    // BCrypt library
    requires jbcrypt;

    // For any AWT/Swing or desktop utilities you might use
    requires java.desktop;

    // Open packages that contain FXML controllers to JavaFX
    opens org.example.rideshareapp to javafx.fxml;
    opens org.example.rideshareapp.controllers to javafx.fxml;
    opens org.example.rideshareapp.services to javafx.fxml;

    // Export packages so other modules (or the launcher) can see them
    exports org.example.rideshareapp;
    exports org.example.rideshareapp.controllers;
    exports org.example.rideshareapp.services;
}
