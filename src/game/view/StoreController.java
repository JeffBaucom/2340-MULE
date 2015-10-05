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
    GameScreenController gameScreenController;
    int food, energy, smithore, crystite, mule;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public StoreController() {
        gameScreenController = main.getGameScreenController();
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
        gameScreenController.enterTown();
    }

    public void returnMap() {
        gameScreenController.returnMap();
    }

    @FXML
    public void buyFoodMule() {
        game.getTurn().buyMuleStore(1);
        returnMap();
    }

    @FXML
    public void buyEnergyMule() {
        game.getTurn().buyMuleStore(2);
        returnMap();
    }

    @FXML
    public void buyOreMule() {
        game.getTurn().buyMuleStore(3);
        returnMap();
    }

    private void updateResources() {
        food = store.getStock("food");
        energy = store.getStock("energy");
        smithore = store.getStock("smithore");
        crystite = store.getStock("crystite");

        // TODO: Add Mule stock
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
        int food = Integer.parseInt(foodField.getText().matches("\\d") ?
                foodField.getText() : "0");
        int energy = Integer.parseInt(energyField.getText().matches("\\d") ?
                energyField.getText() : "0");
        int smithore = Integer.parseInt(smithoreField.getText().matches("\\d") ?
                smithoreField.getText() : "0");
        int crystite = Integer.parseInt(crystiteField.getText().matches("\\d") ?
                crystiteField.getText() : "0");

        int cost = food * store.getCost("food") + energy
                * store.getCost("energy") + smithore
                * store.getCost("smithore") + crystite
                * store.getCost("crystite");

        if (cost <= game.getCurrentPlayer().get("money")) {
            game.getTurn().buyStore("food", food);
            game.getTurn().buyStore("energy", energy);
            game.getTurn().buyStore("smithore", smithore);
            game.getTurn().buyStore("crystite", crystite);
        }

        updateResources();
        clearFields();
        gameScreenController.update();
    }

    @FXML
    public void sellResource() {
        int food = Integer.parseInt(foodField.getText().matches("\\d") ?
                foodField.getText() : "0");
        int energy = Integer.parseInt(energyField.getText().matches("\\d") ?
                energyField.getText() : "0");
        int smithore = Integer.parseInt(smithoreField.getText().matches("\\d") ?
                smithoreField.getText() : "0");
        int crystite = Integer.parseInt(crystiteField.getText().matches("\\d") ?
                crystiteField.getText() : "0");

        // TODO: Use else statements to return input error
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
        gameScreenController.update();
    }
}
