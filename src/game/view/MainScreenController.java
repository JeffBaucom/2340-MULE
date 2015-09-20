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


    int playerCount;

    // Reference to the main application.
    private Main main;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public MainScreenController() {
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

        main.closeScreen();
        main.newGame(playerCount);
        main.showConfigScreen();
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
