package game.view;

import game.model.Game;
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

    private Game game;
    private Store store;
    private GameScreenController gameScreenController;
    private int food, energy, smithore, crystite, mule;

    private static final String FOOD = "food";
    private static final String ENERGY = "energy";
    private static final String SMITHORE = "smithore";
    private static final String CRYSTITE = "crystite";

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

        food = store.getStock(FOOD);
        energy = store.getStock(ENERGY);
        smithore = store.getStock(SMITHORE);
        crystite = store.getStock(CRYSTITE);
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
        food = store.getStock(FOOD);
        energy = store.getStock(ENERGY);
        smithore = store.getStock(SMITHORE);
        crystite = store.getStock(CRYSTITE);

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
        int food = Integer.parseInt(foodField.getText().matches("\\d+") ?
                foodField.getText() : "0");
        int energy = Integer.parseInt(energyField.getText().matches("\\d+") ?
                energyField.getText() : "0");
        int smithore = Integer.parseInt(smithoreField.getText().matches("\\d+") ?
                smithoreField.getText() : "0");
        int crystite = Integer.parseInt(crystiteField.getText().matches("\\d+") ?
                crystiteField.getText() : "0");

        int cost = food * store.getCost(FOOD) + energy
                * store.getCost(ENERGY) + smithore
                * store.getCost(SMITHORE) + crystite
                * store.getCost(CRYSTITE);

        if (cost <= game.getCurrentPlayer().get("money")) {
            game.getTurn().buyStore(FOOD, food);
            game.getTurn().buyStore(ENERGY, energy);
            game.getTurn().buyStore(SMITHORE, smithore);
            game.getTurn().buyStore(CRYSTITE, crystite);
        } else {
            game.logEvent("You don't have enough money.");
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

        if (game.getTurn().sellStore(FOOD, food)) {
            updateResources();
        }
        if (game.getTurn().sellStore(ENERGY, energy)) {
            updateResources();
        }
        if (game.getTurn().sellStore(SMITHORE, smithore)) {
            updateResources();
        }
        if (game.getTurn().sellStore(CRYSTITE, crystite)) {
            updateResources();
        }

        updateResources();
        clearFields();
        gameScreenController.update();
    }
}
