package game.view;

import game.Main;
import game.model.Game;
import game.model.Player;
import game.model.Pub;
import javafx.fxml.FXML;

public class PubController extends Controller {

    Game game;
    Player player;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public PubController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        game = main.getGame();
        store = game.getPub();
        player = game.getCurrentPlayer();
    }

    @FXML
    public void gamble() {
    }

    @FXML
    public void returnTown() {
        main.closeScreen();
        main.showTown();
    }
}
