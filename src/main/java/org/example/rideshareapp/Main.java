package org.example.rideshareapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.rideshareapp.controllers.LoginController;
import org.example.rideshareapp.controllers.MainController;
import org.example.rideshareapp.db.DB;
import org.example.rideshareapp.services.MapService;
import org.example.rideshareapp.services.PaymentService;
import org.example.rideshareapp.services.ProfileService;
import org.example.rideshareapp.services.RideRequestService;

/**
 * Entry point for the RideShare application.
 * <p>
 * This class initializes the database, loads the initial login screen,
 * and provides global navigation methods and shared service instances
 * for all controllers.
 * </p>
 */
public class Main extends Application {

    /** Global singleton instance used for cross-controller access. */
    private static Main instance;

    /**
     * Constructs the application and assigns the singleton instance.
     */
    public Main() {
        instance = this;
    }

    /**
     * Returns the global singleton instance of the application.
     *
     * @return the shared Main instance
     */
    public static Main getInstance() {
        return instance;
    }

    /** Shared profile management service. */
    public static final ProfileService PROFILE_SERVICE = new ProfileService();

    /** Shared payment processing service. */
    public static final PaymentService PAYMENT_SERVICE = new PaymentService();

    /** Shared map and distance calculation service. */
    public static final MapService MAP_SERVICE = new MapService();

    /** Shared ride request service. */
    public static final RideRequestService RIDE_REQUEST_SERVICE =
            new RideRequestService(PAYMENT_SERVICE, MAP_SERVICE);

    /** Primary application window. */
    private Stage primaryStage;

    /**
     * Initializes the application, ensures that the database exists,
     * and loads the login screen as the default view.
     *
     * @param stage the primary stage provided by JavaFX
     * @throws Exception if the login screen cannot be loaded
     */
    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;

        try (var conn = DB.get()) {
            System.out.println("✅ Database ready at: " +
                    new java.io.File("data/app.db").getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }

        showLogin();
    }

    /**
     * Universal logout method used by both Driver and Rider interfaces.
     * Reloads the login screen using the provided stage.
     *
     * @param stage the window in which the login screen should be displayed
     */
    public void logout(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/org/example/rideshareapp/login.fxml")
            );
            Scene scene = new Scene(loader.load());
            LoginController controller = loader.getController();
            controller.setMainApp(this);

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads and displays the login screen on the primary stage.
     *
     * @throws Exception if the FXML file cannot be loaded
     */
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

    /**
     * Loads and displays the user registration page.
     *
     * @throws Exception if the FXML file cannot be loaded
     */
    public void showSignup() throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/org/example/rideshareapp/SignupPage.fxml")
        );
        Scene scene = new Scene(loader.load());
        primaryStage.setTitle("Rideshare - Sign Up");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Loads and displays the main application interface after a successful login.
     *
     * @throws Exception if the FXML file cannot be loaded
     */
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

    /**
     * Launches the JavaFX application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
