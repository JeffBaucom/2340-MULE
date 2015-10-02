package game.view;

import game.model.Game;
import game.model.Player;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TownController extends Controller {
    Game game = main.getGame();
    Player player = game.getCurrentPlayer();

    public TownController() {
    }

    @FXML
    public void initialize() {
    }

    @FXML
    public void enterStore() {
        leaveTown();
        main.showScreen("store");
    }

    @FXML
    public void returnMap() {
        leaveTown();
        main.showScreen("map");
    }

    @FXML
    public void gamblePub() {
        main.getGame().getTurn().gamble();
        leaveTown();
        main.showScreen("map");
    }

    private void leaveTown() {
        main.closeScreen();
    }

    public void update() {
    }
}
