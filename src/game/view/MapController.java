package game.view;

import game.model.Game;
import game.model.Map;
import game.model.Player;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    ImageView cursor, flag;

    @FXML
    GridPane grid;
    @FXML
    Button landButton;
    @FXML
    Label landCost;
    @FXML
    Label playerName;
    @FXML
    Label playerMoney;
    @FXML
    Label playerFood;
    @FXML
    Label playerEnergy;
    @FXML
    Label playerSmithore;
    @FXML
    Label playerCrystite;
    @FXML
    Label playerMule;

    public MapController() {
    }

    @FXML
    public void initialize() {
        game = main.getGame();
        player = game.getCurrentPlayer();
        map = game.getMap();
        cursor = new ImageView(new Image("/game/images/cursor.png"));

        if (game.getPhase() == 0) {
            landButton.setText("Acquire Land");
            landCost.setText("Cost: FREE");
        }

        playerName.setText(player.getName());
        playerMoney.setText("Money: " + player.getMoney());
        playerFood.setText("Food: " + player.getFood());
        playerEnergy.setText("Food: " + player.getEnergy());
        playerSmithore.setText("Food: " + player.getSmithore());
        playerCrystite.setText("Food: " + player.getCrystite());
        playerMule.setText("Food: " + player.getMule());
        playerFood.setText("Food: " + player.getFood());
        flag = new ImageView(new Image("/game/images/flag"
                + player.getColor() + ".png"));

        tiles = new ImageView[5][9];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                ImageView tileImage = new ImageView(new Image
                        ("/game/images/tile" + map.getTile(i, j).getType()
                                + ".png"));
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

    @FXML
    public void handlePass() {
        grid.getChildren().remove(cursor);
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
        player = game.getCurrentPlayer();

        if (game.getPhase() == 1) {
            landButton.setText("Buy Land");
            landCost.setText("Cost: 300");
        }

        playerName.setText(player.getName());
        playerMoney.setText("Money: " + player.getMoney());
        playerFood.setText("Food: " + player.getFood());
        playerEnergy.setText("Food: " + player.getEnergy());
        playerSmithore.setText("Food: " + player.getSmithore());
        playerCrystite.setText("Food: " + player.getCrystite());
        playerMule.setText("Food: " + player.getMule());
        playerFood.setText("Food: " + player.getFood());
        flag = new ImageView(new Image("/game/images/flag"
                + player.getColor() + ".png"));
    }
}
