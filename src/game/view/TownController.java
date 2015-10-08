package game.view;

import game.model.Game;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class TownController extends Controller {
    Game game;
    GameScreenController gameScreenController;

    @FXML
    AnchorPane background;

    public TownController() {
        game = main.getGame();
        gameScreenController = main.getGameScreenController();
    }

    @FXML
    public void initialize() {
        BackgroundImage myBI= new BackgroundImage(new Image
                ("/game/images/townSquare.png"),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        background.setBackground(new Background(myBI));
    }

    @FXML
    public void enterStore() {
        gameScreenController.enterStore();
    }

    @FXML
    public void returnMap() {
        gameScreenController.returnMap();
    }

    @FXML
    public void gamblePub() {
        game.getTurn().gamble();
        game.setTurnover(true);
        gameScreenController.returnMap();
    }

    public void update() {
    }
}
