package game.control;

import game.Main;
import javafx.fxml.FXML;

public class WelcomeScreen {

    // Reference to the main application.
    private Main mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public WelcomeScreen() {
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
