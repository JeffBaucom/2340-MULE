package game.view;


import game.model.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class StoreController extends Controller{
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

    Player player;
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
        food = 16;
        energy = 16;
        smithore = 0;
        crystite = 0;
        mule = 0;

        foodLabel.setText("Food: " + food);
        energyLabel.setText("Energy: " + energy);
        smithoreLabel.setText("Smithore: " + smithore);
        crystiteLabel.setText("Crystite: " + crystite);
        muleLabel.setText("Mule: " + mule);
    }

}
