package game.view;

import game.model.Game;
import game.model.Map;
import game.model.Player;
import game.model.Tile;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.util.Observable;

public class MapController extends Controller {
    Game game;
    Player player;
    Map map;
    Image image;

    @FXML
    GridPane grid;

    public MapController() {
    }

    @FXML
    public void initialize() {
        game = main.getGame();
        player = game.getCurrentPlayer();
        map = game.getMap();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                if (map.getTile(j,i).getType() == "P") {
                    File file = new File("src/game/res/images/tileP.png");
                    Image image = new Image(file.toURI().toString());
                    grid.add(new ImageView(image), i, j);
                } else if(map.getTile(j,i).getType() == "R") {
                    File file = new File("src/game/res/images/tileR.png");
                    Image image = new Image(file.toURI().toString());
                    grid.add(new ImageView(image), i, j);
                } else if(map.getTile(j,i).getType() == "M1") {
                    File file = new File("src/game/res/images/tileM1.png");
                    Image image = new Image(file.toURI().toString());
                    grid.add(new ImageView(image), i, j);
                } else if(map.getTile(j,i).getType() == "M2") {
                    File file = new File("src/game/res/images/tileM2.png");
                    Image image = new Image(file.toURI().toString());
                    grid.add(new ImageView(image), i, j);
                } else if(map.getTile(j,i).getType() == "M3") {
                    File file = new File("src/game/res/images/tileM3.png");
                    Image image = new Image(file.toURI().toString());
                    grid.add(new ImageView(image), i, j);
                } else {
                    File file = new File("src/game/res/images/tileTown.png");
                    Image image = new Image(file.toURI().toString());
                    grid.add(new ImageView(image), i, j);
                }
            }
        }
    }

    @FXML
    public void enterTown() {
        main.closeScreen();
        main.showTown();
    }

    @FXML
    public void select() {
        ObservableList<Node> Tiles = grid.getChildren();
    }

    private String getType(int i, int j) {
        return map.getTile(i, j).getType();
    }
}
