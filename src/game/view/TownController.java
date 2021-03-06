package game.view;

import game.model.Game;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class TownController extends Controller {
    private Game game;
    private GameScreenController gameScreenController;

    @FXML
    AnchorPane background;

    public TownController() {
        game = main.getGame();
        gameScreenController = main.getGameScreenController();
    }

    @FXML
    public final void initialize() {
        BackgroundImage myBI= new BackgroundImage(new Image
                ("/game/resources/images/townSquare.png"),
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

    @FXML
    public void assayLand() {
        game.getTurn().setAssaying(true);
        gameScreenController.returnMap();
    }

    public void update() {
    }
}
