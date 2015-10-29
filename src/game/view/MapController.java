package game.view;

import game.model.Game;
import game.model.Map;
import game.model.Player;
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
    
    GameScreenController gameScreenController;

    @FXML
    GridPane grid;

    @FXML
    public void initialize() {
        game = main.getGame();
        player = game.getCurrentPlayer();
        map = game.getMap();

        gameScreenController = main.getGameScreenController();
        cursor = new ImageView(new Image("/game/resources/images/cursor.png"));
        flag = new ImageView(new Image("/game/resources/images/flag"
                + player.getColor() + ".png"));

        tiles = new ImageView[5][9];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                String resFile;
                Random pRand = new Random();
                Random fRand = new Random();

                if (map.getTile(i, j).getType().equals("P")) {
                    resFile = "/game/resources/images/tile" + map.getTile(i, j).getType()
                            + pRand.nextInt(3) + ".png";
                } else if (map.getTile(i, j).getType().equals("F")) {
                    resFile = "/game/resources/images/tile" + map.getTile(i, j).getType()
                            + fRand.nextInt(6) + ".png";
                } else {
                    resFile = "/game/resources/images/tile"
                            + map.getTile(i, j).getType() + ".png";
                }

                ImageView tileImage = new ImageView(new Image(resFile));
                tiles[i][j] = tileImage;

                if (map.getTile(i, j).getType().equals("T")) {
                    tiles[i][j].setOnMouseClicked(this::enterTown);
                } else {
                    tiles[i][j].setOnMouseClicked(this::selectTile);
                }

                grid.add(tiles[i][j], j, i);

                update();
            }
        }
    }

    public void enterTown(MouseEvent event) {
        gameScreenController.enterTown();
    }

    public void selectTile(MouseEvent event) {
        int row = ((int) event.getSceneY()) / 96;
        int column = ((int) event.getSceneX()) / 96;
        grid.getChildren().remove(cursor);

        if (!game.getTurnOver()) {
            ObservableList<Node> children = grid.getChildren();
            for (Node node : children) {
                if (grid.getRowIndex(node) == row
                        && grid.getColumnIndex(node) == column) {
                    map.setSelectedTile(row, column);
                    grid.add(cursor, column, row);
                    break;
                }
            }

            gameScreenController.enableLandButton();
        }
    }

    public void update() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                int ownerId = map.getTile(i, j).getOwner();
                if (ownerId > -1) {
                    flag = new ImageView(new Image("/game/resources/images/flag"
                            + game.getPlayer(ownerId).getColor() + ".png"));
                    grid.add(flag, j, i);
                }

                int muleId = map.getTile(i, j).getMule();
                if (muleId > 0) {
                    mule = new ImageView(new Image("/game/resources/images/mule"
                            + muleId + ".png"));

                    grid.add(mule, j, i);
                }
            }
        }

        if (game.getTurnOver()) {
            grid.getChildren().remove(cursor);
        }
    }
}
