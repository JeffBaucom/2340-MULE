package game.view;

import game.model.Game;
import game.model.Map;
import game.model.Player;
import javafx.fxml.FXML;
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

    @FXML
    GridPane grid;

    public MapController() {
    }

    @FXML
    public void initialize() {
        game = main.getGame();
        player = game.getCurrentPlayer();
        map = game.getMap();

        tiles = new ImageView[5][9];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                File file = new File("src/game/images/"
                        + map.getTile(i, j).getType() + ".png");
                tiles[i][j] = new ImageView(new Image(file.toURI().toString()));

                if (map.getTile(i, j).getType() == "T") {
                    tiles[i][j].addEventHandler(MouseEvent.MOUSE_CLICKED,
                            event -> enterTown());
                }
                grid.add(tiles[i][j], j, i);
            }
        }
    }

    public void enterTown() {
        main.closeScreen();
        main.showTown();
    }

    private String getType(int i, int j) {
        return map.getTile(i, j).getType();
    }
}
