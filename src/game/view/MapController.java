package game.view;

import game.model.Game;
import game.model.Map;
import game.model.Player;
import game.model.Tile;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.Random;

public class MapController extends Controller {
    Game game;
    Player player;
    Map map;

    Tile currentTile;
    boolean turnOver;

    ImageView[][] tiles;
    ImageView cursor, flag;

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

    public MapController() {
    }

    @FXML
    public void initialize() {
        game = main.getGame();
        player = game.getCurrentPlayer();
        map = game.getMap();
        cursor = new ImageView(new Image("/game/images/cursor.png"));

        landButton.setDisable(true);
        if (game.getPhase() == 0) {
            nextButton.setDisable(true);
            landButton.setText("Acquire Land");
            landCost.setText("Cost: FREE");
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
                Random rand = new Random();

                if (map.getTile(i, j).getType() == "P") {
                    resFile = "/game/images/tile" + map.getTile(i, j).getType()
                            + rand.nextInt(3) + ".png";
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
    }

    @FXML
    public void landAction() {
        ImageView flag = new ImageView(new Image("/game/images/flag"
                + player.getColor() + ".png"));
        grid.add(flag, currentTile.getCol(), currentTile.getRow());
        nextButton.setDisable(false);
        landButton.setDisable(true);

        game.getTurn().buyTile(currentTile.getRow(), currentTile.getCol());

        playerScore.setText(game.getLeaderBoard());
        playerInfo.setText(player.getResourceString());
        nextButton.setText("Next Turn");
        turnOver = true;
    }

    @FXML
    public void handleNext() {
        if (!turnOver) game.passTurn();
        else game.endTurn();

        grid.getChildren().remove(cursor);
        nextTurn();
    }

    private void enterTown(MouseEvent event) {
        // if (game.getPhase() > 1) {
        if (!turnOver) {
            main.closeScreen();
            main.showTown();
        }
        // }
    }

    private void selectTile(MouseEvent event) {
        int row = ((int) event.getSceneY()) / 96;
        int column = ((int) event.getSceneX()) / 96;
        grid.getChildren().remove(cursor);

        if (game.getPhase() < 2) {
            ObservableList<Node> children = grid.getChildren();
            for (Node node : children) {
                if (grid.getRowIndex(node) == row && grid.getColumnIndex(node) ==
                        column) {
                    map.setSelectedTile(row, column);
                    currentTile = map.getSelectedTile();
                    grid.add(cursor, column, row);
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
            landCost.setText("Cost: 300");
            nextButton.setText("Pass");
        } else if (game.getPhase() == 2) {
            landButton.setVisible(false);
            landCost.setVisible(false);
        }

        playerScore.setText(game.getLeaderBoard());
        playerName.setText(player.getName());
        playerInfo.setText(player.getResourceString());
        flag = new ImageView(new Image("/game/images/flag"
                + player.getColor() + ".png"));

        turnOver = false;
    }
}
