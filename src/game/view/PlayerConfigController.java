package game.view;

import game.model.Game;
import game.model.Race;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PlayerConfigController extends Controller {
    @FXML
    Label playerText;
    @FXML
    TextField playerNameField;
    @FXML
    Button next;
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
    String playerName, colorValue;
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
        playerNameField.setText("");
        next.setDisable(true);

    }

    @FXML
    public void enableNext() {
        next.setDisable(playerNameField.getText().equals("")
                || color.getSelectedToggle() == null
                || race.getSelectedToggle() == null);
    }

    @FXML
    private void handleNext() {
        game = main.getGame();

        playerName = playerNameField.getText();

        if (red.isSelected()) colorValue = "red";
        else if (blue.isSelected()) colorValue = "blue";
        else if (yellow.isSelected()) colorValue = "yellow";
        else colorValue = "green";

        if (flapper.isSelected()) raceValue = Race.FLAPPER;
        else if (human.isSelected()) raceValue = Race.HUMAN;
        else raceValue = Race.OTHER;

        game.newPlayer(playerIndex, playerName, colorValue, raceValue);

        if (playerIndex == game.getPlayerCount() - 1) {
            main.closeScreen();
            main.generateMap();
            main.showMap();
        } else {
            nextPlayer();
        }
    }

    private void nextPlayer() {
        playerIndex++;
        playerText.setText("Player " + (playerIndex + 1));

        playerNameField.setText("");
        playerNameField.requestFocus();
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
