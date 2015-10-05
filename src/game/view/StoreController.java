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

    @FXML
    public void buyFoodMule() {
        game.getTurn().buyMuleStore(1);
        returnTown();
    }

    @FXML
    public void buyEnergyMule() {

        game.getTurn().buyMuleStore(2);
        returnTown();
    }

    @FXML
    public void buyOreMule() {

        game.getTurn().buyMuleStore(3);
        returnTown();
    }

    private void updateResources() {
        food = store.getStock("food");
        energy = store.getStock("energy");
        smithore = store.getStock("smithore");
        crystite = store.getStock("crystite");
        // TO DO: Add Mule stock
        foodLabel.setText("Food: " + food );
        energyLabel.setText("Energy: " + energy);
        smithoreLabel.setText("Smithore: " + smithore);
        crystiteLabel.setText("Crystite: " + crystite);
        muleLabel.setText("Mule: " + mule);
    }

    private void clearFields() {
        foodField.setText("0");
        energyField.setText("0");
        smithoreField.setText("0");
        crystiteField.setText("0");
    }

    @FXML
    public void buyResource() {
        int food = Integer.parseInt(foodField.getText());
        int energy = Integer.parseInt(energyField.getText());
        int smithore = Integer.parseInt(smithoreField.getText());
        int crystite = Integer.parseInt(crystiteField.getText());
        int cost = food * store.getCost("food") + energy * store.getCost("energy") + smithore * store.getCost("smithore") + crystite * store.getCost("crystite");
        if (cost <= game.getCurrentPlayer().get("money")) {
            game.getTurn().buyStore("food", food);
            game.getTurn().buyStore("energy", energy);
            game.getTurn().buyStore("smithore", smithore);
            game.getTurn().buyStore("crystite", crystite);
        }
        updateResources();
        clearFields();
        returnTown();
    }

    @FXML
    public void sellResource() {
        int food = Integer.parseInt(foodField.getText());
        int energy = Integer.parseInt(energyField.getText());
        int smithore = Integer.parseInt(smithoreField.getText());
        int crystite = Integer.parseInt(crystiteField.getText());
        // TO DO: Use else statements to return input error
        if (game.getTurn().sellStore("food", food)) {
            updateResources();
        }
        if (game.getTurn().sellStore("energy", energy)) {
            updateResources();
        }
        if (game.getTurn().sellStore("smithore", smithore)) {
            updateResources();
        }
        if (game.getTurn().sellStore("crystite", crystite)) {
            updateResources();
        }
        updateResources();
        clearFields();
        returnTown();
    }
}
