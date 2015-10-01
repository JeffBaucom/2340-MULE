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

    Timer timer;
    int timeLeft;

    @FXML
    Label clock;

    public TownController() {
    }

    @FXML
    public void initialize() {
    }

    @FXML
    public void enterStore() {
        main.closeScreen();
        main.showStore();
    }

    @FXML
    public void returnMap() {
        timer.cancel();
        main.closeScreen();
        main.showMap();
    }

    @FXML
    public void gamblePub() {
        timer.cancel();
        main.getGame().getTurn().gamble(timeLeft);
        main.closeScreen();
        main.showMap();
    }

    public void update() {
        timer = new Timer();
        getTimerTask();
    }

    public void getTimerTask() {
        timeLeft = game.getTimeLeft();

        int seconds = timeLeft;
        timer.schedule(new TurnEnder(), seconds * 1000);
        timer.schedule(new ClockUpdater(), new Date(), 1000);
    }

    private class TurnEnder extends TimerTask {
        public void run() {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    returnMap();
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
