module org.example.rideshareapp {
    // JavaFX
    requires javafx.controls;
    requires javafx.fxml;

    // ✅ Needed for JDBC classes used in DB.java & UserDao.java
    requires java.sql;

    // ✅ Needed for BCrypt (org.mindrot.jbcrypt.BCrypt)
    // The jar provides an automatic module named 'jbcrypt'
    requires jbcrypt;
    requires javafx.graphics;
    requires java.desktop;

    // FXML controllers live in this package, so open it for reflection
    opens org.example.rideshareapp to javafx.fxml;
    // If you load any controllers from subpackages via FXML, open them too:
    opens org.example.rideshareapp.auth to javafx.fxml;
    // (DB isn’t an FXML controller, opening db is optional, but harmless)
    opens org.example.rideshareapp.db to javafx.fxml;

    // Export your base package if other modules need to compile against it
    exports org.example.rideshareapp;
    exports org.example.rideshareapp.controllers;
    opens org.example.rideshareapp.controllers to javafx.fxml;
    exports org.example.rideshareapp.services;
    opens org.example.rideshareapp.services to javafx.fxml;
}
