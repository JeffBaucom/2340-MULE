package game.view;

import game.Main;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;

public class WelcomeScreenController {
    @FXML
    RadioButton players2;
    @FXML
    RadioButton players3;
    @FXML
    RadioButton players4;

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
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewGame() {
        int players;
        if(players2.isSelected()) players = 2;
        else if(players3.isSelected()) players = 3;
        else players = 4;

        for (int i = 0; i < players; i++) {
            mainApp.showConfigScreen(players);
        }
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