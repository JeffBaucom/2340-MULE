package game.view;

import game.model.Game;
import game.model.Map;
import game.model.Player;
import game.model.Tile;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;

import java.util.Random;

public class GameScreenController extends Controller {
    Game game;
    Player player;
    Map map;

    Timer timer;
    int timeLeft;

    @FXML
    Button landButton;
    @FXML
    Button nextButton;
    @FXML
    Label playerScore;
    @FXML
    Label playerName;
    @FXML
    Label playerInfo;
    @FXML
    TextArea gameLog;
    @FXML
    Label clock;

    public GameScreenController() {
    }

    @FXML
    public void initialize() {
        game = main.getGame();
        player = game.getCurrentPlayer();
        map = game.getMap();

        gameLog.setEditable(false);
        gameLog.setText(game.getGameLog());

        landButton.setDisable(true);
        if (game.getPhase() == 0) {
            nextButton.setDisable(true);
            landButton.setText("Acquire Land");
        }

        playerScore.setText(game.getLeaderBoard());
        playerName.setText(player.getName());
        playerInfo.setText(player.getResourceString());

        nextButton.setText("Next Turn");
        timer = new Timer();
        getTimerTask();
    }

    @FXML
    public void landAction() {
        // TODO have MapScreen add flag to tile
        Tile currentTile = map.getSelectedTile();
        nextButton.setDisable(false);
        landButton.setDisable(true);

        game.getTurn().buyTile(currentTile.getRow(), currentTile.getCol());

        game.logEvent(player.getName() + " acquired " + "land plot ("
                + currentTile.getRow() + ", " + currentTile.getCol() + ").");
        nextButton.setText("Next Turn");

        timer.cancel();
        timer = new Timer();
        clock.setText("Turn Over.");
        game.setTurnover(true);
        update();
    }

    @FXML
    public void handleNext() {
        if (!game.getTurnOver()) game.passTurn();

        timer.cancel();
        timer = new Timer();
        getTimerTask();
        nextTurn();
    }

    private void enterTown() {
        // TODO show town screen, update interface
    }

    private void nextTurn() {
        player = game.getCurrentPlayer();

        if (game.getPhase() == 0) {
            nextButton.setDisable(true);
        } else if (game.getPhase() == 1) {
            landButton.setText("Buy Land");
            nextButton.setText("Pass");
        } else if (game.getPhase() == 2) {
            landButton.setVisible(false);
        }

        update();
        game.setTurnover(false);
    }

    public void update() {
        playerName.setText(player.getName());
        playerInfo.setText(player.getResourceString());
        playerScore.setText(game.getLeaderBoard());

        gameLog.setText(game.getGameLog());
        gameLog.setScrollTop(Double.MAX_VALUE);

        if (game.getCurrentPlayer() != player) {
            timer.cancel();
            timer = new Timer();
            clock.setText("Turn Over.");
            nextButton.setText("Next Turn");
            nextButton.setDisable(false);
            game.setTurnover(true);
        }
    }

    public void getTimerTask() {
        int round = game.getRoundCounter();
        int food = player.get("food");
        if (round < 4) {
            if (food == 0) {
                timeLeft = 5;
                timer.schedule(new TurnEnder(), 5*1000);
                timer.schedule(new ClockUpdater(), new Date(), 1000);
            } else if (food >= 3) {
                timeLeft = 50;
                timer.schedule(new TurnEnder(), 50*1000);
                timer.schedule(new ClockUpdater(), new Date(), 1000);
            } else {
                timeLeft = 30;
                timer.schedule(new TurnEnder(), 30*1000);
                timer.schedule(new ClockUpdater(), new Date(), 1000);
            }
        } else if (round > 7) {
            if (food == 0) {
                timeLeft = 5;
                timer.schedule(new TurnEnder(), 5*1000);
                timer.schedule(new ClockUpdater(), new Date(), 1000);
            } else if (food >= 5) {
                timeLeft = 50;
                timer.schedule(new TurnEnder(), 50*1000);
                timer.schedule(new ClockUpdater(), new Date(), 1000);
            } else {
                timeLeft = 30;
                timer.schedule(new TurnEnder(), 30*1000);
                timer.schedule(new ClockUpdater(), new Date(), 1000);
            }
        } else {
            if (food == 0) {
                timeLeft = 5;
                timer.schedule(new TurnEnder(), 5*1000);
                timer.schedule(new ClockUpdater(), new Date(), 1000);
            } else if (food >= 4) {
                timeLeft = 50;
                timer.schedule(new TurnEnder(), 50*1000);
                timer.schedule(new ClockUpdater(), new Date(), 1000);
            } else {
                timeLeft = 30;
                timer.schedule(new TurnEnder(), 30*1000);
                timer.schedule(new ClockUpdater(), new Date(), 1000);
            }
        }
    }

    private class TurnEnder extends TimerTask {
        public void run() {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    game.setTurnover(true);
                    game.endTurn();
                    handleNext();
                    timer.cancel();
                    timer = new Timer();
                    getTimerTask();
                }
            });
        }
    }

    private class ClockUpdater extends TimerTask {
        public void run() {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    clock.setText("Time Remaining in Turn: " + timeLeft);
                    timeLeft--;
                }
            });
        }
    }
}
