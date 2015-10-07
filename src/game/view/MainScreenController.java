package game.view;

import game.Main;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;

public class MainScreenController extends Controller{
    @FXML
    RadioButton players2;
    @FXML
    RadioButton players3;
    @FXML
    RadioButton players4;
    @FXML
    RadioButton beginner;
    @FXML
    RadioButton standardDiff;
    @FXML
    RadioButton tournament;
    @FXML
    RadioButton random;
    @FXML
    RadioButton standard;

    int playerCount;
    int difficulty;
    String mapType;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public MainScreenController() {
        super();
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewGame() {
        if(players2.isSelected()) playerCount = 2;
        else if(players3.isSelected()) playerCount = 3;
        else playerCount = 4;

        if(beginner.isSelected()) difficulty = 0;
        else if(standardDiff.isSelected()) difficulty = 1;
        else difficulty = 2;

        if(standard.isSelected()) mapType = "standard";
        else mapType = "random";

        main.closeScreen();
        main.newGame(playerCount, difficulty, mapType);
        main.showScreen("player config");
    }
}
