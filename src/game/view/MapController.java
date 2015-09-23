package game.view;

import game.model.Game;
import game.model.Map;
import game.model.Player;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import java.io.File;

public class MapController extends Controller {
    Game game;
    Player player;
    Map map;
    ImageView[][] tiles;
    ImageView cursor;

    @FXML
    GridPane grid;

    public MapController() {
    }

    @FXML
    public void initialize() {
        game = main.getGame();
        player = game.getCurrentPlayer();
        map = game.getMap();
        cursor = new ImageView(new Image("/game/images/cursor"
                + player.getColor() + ".png"));

        tiles = new ImageView[5][9];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                ImageView tileImage = new ImageView(new Image
                        ("/game/images/tile"
                        + map.getTile(i, j).getType() + ".png"));
                tiles[i][j] = tileImage;

                if (map.getTile(i, j).getType() == "T") {
                    tiles[i][j].setOnMouseClicked(this::enterTown);
                } else if (map.getTile(i, j).getType() != "R") {
                    tiles[i][j].setOnMouseClicked(this::selectTile);
                }

                grid.add(tiles[i][j], j, i);
            }
        }
    }

    @FXML
    public void handlePass() {
        game.passTurn();
        nextTurn();

    }

    private void enterTown(MouseEvent event) {
        main.closeScreen();
        main.showTown();
    }

    private void selectTile(MouseEvent event) {
        int row = ((int) event.getSceneY()) / 96;
        int column = ((int) event.getSceneX()) / 96;
        grid.getChildren().remove(cursor);

        ObservableList<Node> children = grid.getChildren();
        for(Node node : children) {
            if(grid.getRowIndex(node) == row && grid.getColumnIndex(node) ==
                    column) {
                map.setSelectedTile(row, column);
                grid.add(cursor, column, row);
                break;
            }
        }
    }

    private void nextTurn() {
        initialize();
    }
}
