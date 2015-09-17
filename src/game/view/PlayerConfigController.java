package game.view;

import game.Main;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;

public class PlayerConfigController {
    @FXML
    RadioButton red;
    @FXML
    RadioButton blue;
    @FXML
    RadioButton green;
    @FXML
    RadioButton yellow;
    // Reference to the main application.
    private Main mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public PlayerConfigController() {
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
    private void handleColor() {
        RadioButton chosen;
        if(red.isSelected()) chosen = red;
        else if(blue.isSelected()) chosen = blue;
        else if(green.isSelected()) chosen = green;
        else chosen = yellow;

        mainApp.updatePlayerColors(chosen);
    }

    @FXML
    private void disableButtons(ArrayList<RadioButton> chosenColors) {
        for (RadioButton b: chosenColors) {
            b.setDisable(true);
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
