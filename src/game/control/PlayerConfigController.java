package game.control;

import game.Main;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;

import java.util.ArrayList;

public class PlayerConfigController extends Controller {
    @FXML
    RadioButton red;
    @FXML
    RadioButton blue;
    @FXML
    RadioButton green;
    @FXML
    RadioButton yellow;

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
    private void handleNext() {
        main.closeScreen();
    }
}
