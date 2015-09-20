package game.view;

import game.model.Color;
import game.model.Game;
import game.model.Race;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class PlayerConfigController extends Controller {
    @FXML
    Label playerText;
    @FXML
    TextField playerNameField;
    @FXML
    ToggleGroup race;
    @FXML
    ToggleGroup color;
    @FXML
    RadioButton flapper;
    @FXML
    RadioButton human;
    @FXML
    RadioButton other;
    @FXML
    RadioButton red;
    @FXML
    RadioButton blue;
    @FXML
    RadioButton green;
    @FXML
    RadioButton yellow;

    int playerIndex;
    String playerName;
    Color colorValue;
    Race raceValue;
    Game game;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        System.out.println("player config");
        playerIndex = 0;
        playerText.setText("Player 1");
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNext() {
        game = main.getGame();

        playerName = playerNameField.getText();

        if (red.isSelected()) colorValue = Color.RED;
        else if (blue.isSelected()) colorValue = Color.BLUE;
        else if (yellow.isSelected()) colorValue = Color.YELLOW;
        else colorValue = Color.GREEN;

        if (flapper.isSelected()) raceValue = Race.FLAPPER;
        else if (human.isSelected()) raceValue = Race.HUMAN;
        else raceValue = Race.OTHER;

        if (playerIndex == game.getPlayerCount() - 1) {
            main.closeScreen();
        }

        playerIndex++;
        playerText.setText("Player " + (playerIndex + 1));
        System.out.println(playerName);
        red.setDisable(true);
    }

    private void nextPlayer() {
        playerIndex++;
        playerText.setText("Player " + (playerIndex + 1));
        System.out.println(playerName);

        red.setSelected(false);
    }
}
