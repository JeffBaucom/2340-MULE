package game.view;

import game.Main;
import game.model.Game;
import game.model.Map;
import game.model.Player;
import game.model.Tile;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.Random;

public class MapController extends Controller {
    Game game;
    Player player;
    Map map;

    ImageView[][] tiles;
    ImageView cursor, flag, mule;

    @FXML
    GridPane grid;

    @FXML
    public void initialize() {
        game = main.getGame();
        player = game.getCurrentPlayer();
        map = game.getMap();

        cursor = new ImageView(new Image("/game/images/cursor.png"));
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
    }

    public void enterTown(MouseEvent event) {
        // TODO change GameScreen interface, display Town
    }

    public void selectTile(MouseEvent event) {
        int row = ((int) event.getSceneY()) / 96;
        int column = ((int) event.getSceneX()) / 96;
        grid.getChildren().remove(cursor);

        if (game.getPhase() < 2 && !game.getTurnOver()) {
            ObservableList<Node> children = grid.getChildren();
            for (Node node : children) {
                if (grid.getRowIndex(node) == row
                        && grid.getColumnIndex(node) == column) {
                    map.setSelectedTile(row, column);
                    grid.add(cursor, column, row);
                }
            }

            // TODO update GameScreen to show correct action
        }
    }

    public void update() {
        player = game.getCurrentPlayer();
        flag = new ImageView(new Image("/game/images/flag"
                + player.getColor() + ".png"));
    }
}
