package game.control;

import game.Main;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;

import java.util.ArrayList;

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
    ArrayList<RadioButton> radioButtons;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public PlayerConfigController() {
        radioButtons = new ArrayList<RadioButton>();
        radioButtons.add(red);
        radioButtons.add(blue);
        radioButtons.add(green);
        radioButtons.add(yellow);
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
    private void handleNext() {
        RadioButton chosen;
        if(red.isSelected()) chosen = red;
        else if(blue.isSelected()) chosen = blue;
        else if(green.isSelected()) chosen = green;
        else chosen = yellow;

        mainApp.updatePlayerColors(chosen);
        mainApp.closeConfigScreen();
    }

    @FXML
    public void disableButtons(ArrayList<RadioButton> chosenColors) {
        for (RadioButton b : chosenColors) {
            for (RadioButton c : radioButtons) {
                if (b.getId().equals(c.getId())) {
                    c.setDisable(true);
                }
            }
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
