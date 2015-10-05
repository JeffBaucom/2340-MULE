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
    Game game;
    GameScreenController gameScreenController;

    public TownController() {
        game = main.getGame();
        gameScreenController = main.getGameScreenController();
    }

    @FXML
    public void initialize() {
    }

    @FXML
    public void enterStore() {
        gameScreenController.enterStore();
    }

    @FXML
    public void returnMap() {
        gameScreenController.returnMap();
    }

    @FXML
    public void gamblePub() {
        game.getTurn().gamble();
        game.setTurnover(true);
        gameScreenController.returnMap();
    }

    public void update() {
    }
}
