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
import javafx.scene.layout.GridPane;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;

import java.util.Random;

public class MapController extends Controller {
    Game game;
    Player player;
    Map map;

    Tile currentTile;
    boolean turnOver;

    ImageView[][] tiles;
    ImageView cursor, flag, mule;

    Timer timer;
    int timeLeft;

    @FXML
    GridPane grid;
    @FXML
    Button landButton;
    @FXML
    Button nextButton;
    @FXML
    Label landCost;
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

    public MapController() {
    }

    @FXML
    public void initialize() {
        game = main.getGame();
        player = game.getCurrentPlayer();
        map = game.getMap();
        cursor = new ImageView(new Image("/game/images/cursor.png"));

        gameLog.setEditable(false);
        gameLog.setText(game.getGameLog());

        landButton.setDisable(true);
        if (game.getPhase() == 0) {
            nextButton.setDisable(true);
            landButton.setText("Acquire Land");
            landCost.setText("Land is currently free.");
        }

        playerScore.setText(game.getLeaderBoard());
        playerName.setText(player.getName());
        playerInfo.setText(player.getResourceString());
        flag = new ImageView(new Image("/game/images/flag"
                + player.getColor() + ".png"));

        tiles = new ImageView[5][9];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                String resFile;
                Random pRand = new Random();
                Random fRand = new Random();

                if (map.getTile(i, j).getType() == "P") {
                    resFile = "/game/images/tile" + map.getTile(i, j).getType()
                            + pRand.nextInt(3) + ".png";
                } else if (map.getTile(i, j).getType() == "F") {
                    resFile = "/game/images/tile" + map.getTile(i, j).getType()
                            + fRand.nextInt(6) + ".png";
                } else {
                    resFile = "/game/images/tile"
                            + map.getTile(i, j).getType() + ".png";
                }

                ImageView tileImage = new ImageView(new Image(resFile));
                tiles[i][j] = tileImage;

                if (map.getTile(i, j).getType() == "T") {
                    tiles[i][j].setOnMouseClicked(this::enterTown);
                } else {
                    tiles[i][j].setOnMouseClicked(this::selectTile);
                }

                grid.add(tiles[i][j], j, i);
            }
        }

        turnOver = false;
        nextButton.setText("Next Turn");
        timer = new Timer();
        getTimerTask();
    }

    @FXML
    public void landAction() {
        ImageView flag = new ImageView(new Image("/game/images/flag"
                + player.getColor() + ".png"));
        grid.add(flag, currentTile.getCol(), currentTile.getRow());
        grid.getChildren().remove(cursor);
        nextButton.setDisable(false);
        landButton.setDisable(true);

        game.getTurn().buyTile(currentTile.getRow(), currentTile.getCol());

        game.logEvent(player.getName() + " acquired " + "land plot ("
                + currentTile.getRow() + ", " + currentTile.getCol() + ").");
        nextButton.setText("Next Turn");

        timer.cancel();
        timer = new Timer();
        clock.setText("Turn Over.");
        turnOver = true;
        update();
    }

    @FXML
    public void handleNext() {
        if (!turnOver) game.passTurn();

        grid.getChildren().remove(cursor);

        timer.cancel();
        timer = new Timer();
        getTimerTask();
        nextTurn();
    }

    private void enterTown(MouseEvent event) {
        if (game.getPhase() > 0) {
            if (!turnOver) {
                main.closeScreen();
                main.showTown();
            }
        } else {
            game.logEvent("You can't go to town yet!");
            update();
        }
    }

    private void selectTile(MouseEvent event) {
        int row = ((int) event.getSceneY()) / 96;
        int column = ((int) event.getSceneX()) / 96;
        grid.getChildren().remove(cursor);

        if (game.getPhase() < 2 && !turnOver) {
            ObservableList<Node> children = grid.getChildren();
            for (Node node : children) {
                if (grid.getRowIndex(node) == row
                        && grid.getColumnIndex(node) == column) {
                    map.setSelectedTile(row, column);
                    currentTile = map.getSelectedTile();
                    grid.add(cursor, column, row);
                    if ((currentTile.getMule() == 0) && (currentTile.getOwner() == player.getId())) {
                        currentTile.setMule(player.getMule());
                        mule = new ImageView(new Image("/game/images/mule"
                                + player.getMule() + ".png"));
                        grid.add(mule, column, row);
                    }
                    player.setMule(0);
                    break;
                }
            }

            landButton.setDisable(currentTile.getOwner() != -1 || turnOver);
        }
    }

    private void nextTurn() {
        player = game.getCurrentPlayer();

        if (game.getPhase() == 0) {
            nextButton.setDisable(true);
        } else if (game.getPhase() == 1) {
            landButton.setText("Buy Land");
            landCost.setText("Land is currently $300.");
            nextButton.setText("Pass");
        } else if (game.getPhase() == 2) {
            landButton.setVisible(false);
            landCost.setVisible(false);
        }

        update();
        turnOver = false;
    }

    public void update() {
        playerName.setText(player.getName());
        playerInfo.setText(player.getResourceString());
        playerScore.setText(game.getLeaderBoard());
        flag = new ImageView(new Image("/game/images/flag"
                + player.getColor() + ".png"));

        gameLog.setText(game.getGameLog());
        gameLog.setScrollTop(Double.MAX_VALUE);

        if (game.getCurrentPlayer() != player) {
            turnOver = true;
            nextButton.setText("Next Turn");
            nextButton.setDisable(false);
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
                    turnOver = true;
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

    public void setTurnOver(boolean b) {
        turnOver = b;
    }
}
