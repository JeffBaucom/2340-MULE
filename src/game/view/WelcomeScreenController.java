package game.view;

import game.Main;
import javafx.fxml.FXML;

public class WelcomeScreenController {

    // Reference to the main application.
    private Main mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public WelcomeScreenController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp main App
     */
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
}
