package game.view;

import game.Main;
import game.model.Game;
import game.model.Player;
import game.model.Store;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class StoreController extends Controller {
    @FXML
    Label foodLabel;
    @FXML
    Label energyLabel;
    @FXML
    Label smithoreLabel;
    @FXML
    Label crystiteLabel;
    @FXML
    Label muleLabel;
    @FXML
    TextField foodField;
    @FXML
    TextField energyField;
    @FXML
    TextField smithoreField;
    @FXML
    TextField crystiteField;
    @FXML
    TextField muleField;

    Game game;
    Player player;
    Store store;
    int food, energy, smithore, crystite, mule;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public StoreController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        game = main.getGame();
        store = game.getStore();
        player = game.getCurrentPlayer();

        food = store.getStock("food");
        energy = store.getStock("energy");
        smithore = store.getStock("smithore");
        crystite = store.getStock("crystite");
        mule = store.getStock("mule");

        foodField.setText("0");
        energyField.setText("0");
        smithoreField.setText("0");
        crystiteField.setText("0");
        muleField.setText("0");

        foodLabel.setText("Food: " + food );
        energyLabel.setText("Energy: " + energy);
        smithoreLabel.setText("Smithore: " + smithore);
        crystiteLabel.setText("Crystite: " + crystite);
        muleLabel.setText("Mule: " + mule);
    }

    @FXML
    public void returnTown() {
        main.closeScreen();
        main.showScreen("town");
    }
}
