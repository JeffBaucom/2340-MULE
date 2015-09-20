package game.control;

import game.Main;
import game.model.Color;
import game.model.Game;
import game.model.Race;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PlayerConfigController extends Controller implements Initializable {
    @FXML
    RadioButton red;
    @FXML
    RadioButton blue;
    @FXML
    RadioButton green;
    @FXML
    RadioButton yellow;
    @FXML
    Label playerText;

    int playerIndex;
    Color color;
    Race race;
    Game game;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        playerIndex = 0;
        playerText.setText("Player " + (playerIndex + 1));
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNext() {
        game = main.getGame();

        if (red.isSelected()) color = Color.RED;
        else if (blue.isSelected()) color = Color.BLUE;
        else if (yellow.isSelected()) color = Color.YELLOW;
        else color = Color.GREEN;

        if (playerIndex == game.getPlayerCount()) {
            main.closeScreen();
        }

        playerIndex++;
    }
}
