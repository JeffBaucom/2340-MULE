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
        playerIndex = 0;
        playerText.setText("Player 1");
    }

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
            main.showMap();
        } else {
            nextPlayer();
        }
    }

    private void nextPlayer() {
        playerIndex++;
        playerText.setText("Player " + (playerIndex + 1));

        playerNameField.setText("");
        red.setDisable(red.isSelected() || red.isDisabled());
        blue.setDisable(blue.isSelected() || blue.isDisabled());
        green.setDisable(green.isSelected() || green.isDisabled());
        yellow.setDisable(yellow.isSelected() || yellow.isDisabled());

        red.setSelected(false);
        blue.setSelected(false);
        green.setSelected(false);
        yellow.setSelected(false);

        flapper.setSelected(false);
        human.setSelected(false);
        other.setSelected(false);
    }
}
