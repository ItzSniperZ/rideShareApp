package org.example.rideshareapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.rideshareapp.controllers.LoginController;
import org.example.rideshareapp.controllers.MainController;
import org.example.rideshareapp.db.DB; // ✅ make sure this import is here
import org.example.rideshareapp.services.MapService;
import org.example.rideshareapp.services.PaymentService;
import org.example.rideshareapp.services.ProfileService;
import org.example.rideshareapp.services.RideRequestService;

public class Main extends Application {

    // Shared services for controllers
    public static final ProfileService PROFILE_SERVICE = new ProfileService();
    public static final PaymentService PAYMENT_SERVICE = new PaymentService();
    public static final MapService MAP_SERVICE = new MapService();
    public static final RideRequestService RIDE_REQUEST_SERVICE =
            new RideRequestService(PAYMENT_SERVICE, MAP_SERVICE);

    private Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;

        // ✅ Initialize database (creates data/app.db if missing)
        try (var conn = DB.get()) {
            System.out.println("✅ Database ready at: " +
                    new java.io.File("data/app.db").getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // ✅ Load login screen (no test user)
        showLogin();
    }

    /** Loads the login screen */
    public void showLogin() throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/org/example/rideshareapp/login.fxml")
        );
        Scene scene = new Scene(loader.load());
        LoginController controller = loader.getController();
        controller.setMainApp(this);

        primaryStage.setTitle("Rideshare - Login");
        primaryStage.setScene(scene);
        primaryStage.setWidth(950);
        primaryStage.setHeight(600);
        primaryStage.show();
    }
    public void showSignup() throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/org/example/rideshareapp/SignupPage.fxml")
        );
        Scene scene = new Scene(loader.load());

        primaryStage.setTitle("Rideshare - Sign Up");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /** Loads the main application (after login) */
    public void showMainApp() throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/org/example/rideshareapp/main.fxml")
        );
        Scene scene = new Scene(loader.load());
        MainController controller = loader.getController();
        controller.setMainApp(this);

        primaryStage.setTitle("Rideshare - App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
