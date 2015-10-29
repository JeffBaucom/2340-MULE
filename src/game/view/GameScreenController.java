package game.view;

import game.model.Game;
import game.model.Map;
import game.model.Player;
import game.model.Tile;
import javafx.fxml.FXML;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;

public class GameScreenController extends Controller {
    Game game;
    Player player;
    Map map;

    Timer timer;
    int timeLeft;
    MediaPlayer mediaPlayer;

    @FXML
    Button landButton;
    @FXML
    Button nextButton;
    @FXML
    Label playerScore;
    @FXML
    Label playerName;
    @FXML
    ImageView playerFlag;
    @FXML
    Label playerInfo;
    @FXML
    TextArea gameLog;
    @FXML
    Label clock;

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
        playerFlag.setImage(new Image("/game/resources/images/flag"
                + player.getColor() + ".png"));

        String musicFile = "src/game/resources/music/map.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();

        nextButton.setText("Next Turn");
        timer = new Timer();
        getTimerTask();
        update();
    }

    @FXML
    public void landAction() {
        Tile currentTile = map.getSelectedTile();
        nextButton.setDisable(false);
        landButton.setDisable(true);

        if (player.getMule() > 0) {
            if (currentTile.getOwner() == player.getId()
                    && currentTile.getMule() == 0) {
                game.getTurn().placeMule(currentTile.getRow(),
                        currentTile.getCol());
            } else {
                game.getTurn().loseMule();
                game.logEvent("You lost your Mule!");
            }
            player.setMule(0);
            game.setTurnover(true);
        } else {
            if (game.getTurn().buyTile(currentTile.getRow(), currentTile
                    .getCol())) {
                game.logEvent(player.getName() + " acquired " + "land plot ("
                        + currentTile.getRow() + ", " + currentTile.getCol() + ").");
                game.setTurnover(true);
            }
        }

        main.closeScreen();
        main.showScreen("map");
        update();
    }

    @FXML
    public void handleNext() {
        if (!game.getTurnOver())  {
            game.passTurn();
            game.setTurnover(true);
            timer.cancel();
            timer = new Timer();
            returnMap();
            update();
        } else {
            nextTurn();
        }
    }

    @FXML
    public void saveGame() {
        handleNext();
        main.saveGame();
        main.quit();
    }

    public void enableLandButton() {
        if (player.getMule() > 0 || (map.getSelectedTile().getOwner() ==
                -1 && game.getPhase() < 2)) {
            landButton.setDisable(false);
            landButton.setVisible(true);
        } else {
            landButton.setDisable(true);
        }
    }

    public void enterTown() {
        if (!game.getTurnOver()) {
            if (game.getPhase() > 0) {
                landButton.setVisible(false);
                main.closeScreen();
                main.showScreen("town");

                mediaPlayer.stop();
                String musicFile = "src/game/resources/music/town.mp3";
                Media sound = new Media(new File(musicFile).toURI().toString());
                mediaPlayer = new MediaPlayer(sound);
                mediaPlayer.play();
            } else {
                game.logEvent("You cannot go to town yet!");
                update();
            }
        }
    }

    public void enterStore() {
        main.closeScreen();
        main.showScreen("store");

        mediaPlayer.stop();
        String musicFile = "src/game/resources/music/shop.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    public void returnMap() {
        main.closeScreen();
        main.showScreen("map");

        if (game.getPhase() < 2) {
            landButton.setVisible(true);
        }

        if (player.getMule() > 0) {
            landButton.setText("Place Mule");
        }

        mediaPlayer.stop();
        String musicFile = "src/game/resources/music/map.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
        update();
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
            nextButton.setText("Pass");
        }

        game.setTurnover(false);
        getTimerTask();
        update();

        if (!game.getTurn().getRandomEventMessage().equals("")) {
            showRandomEvent();
        }
    }

    public void update() {
        playerName.setText(player.getName());
        playerInfo.setText(player.getResourceString());
        playerScore.setText(game.getLeaderBoard());
        playerFlag.setImage(new Image("/game/resources/images/flag"
                + player.getColor() + ".png"));

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

        if (player.getMule() < 1 && game.getPhase() >= 1) {
            landButton.setText("Buy Land");
        }

        if (game.getTurnOver()) {
            timer.cancel();
            timer = new Timer();
            landButton.setDisable(true);
            nextButton.setText("Next Turn");
            clock.setText("Turn Over");
        }
    }

    private void showRandomEvent() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GameScreenController.class.getResource
                    ("/game/view/RandomEvent.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("RANDOM EVENT");
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
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
            Platform.runLater(() -> {
                game.setTurnover(true);
                game.endTurn();
                returnMap();
            });
        }
    }

    private class ClockUpdater extends TimerTask {
        public void run() {
            Platform.runLater(() -> {
                clock.setText("Time Remaining in Turn: " + timeLeft);
                timeLeft--;
            });
        }
    }
}
