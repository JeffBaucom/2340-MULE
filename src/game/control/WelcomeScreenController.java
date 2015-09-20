package game.control;

import game.Main;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;

public class WelcomeScreenController extends Controller{
    @FXML
    RadioButton players2;
    @FXML
    RadioButton players3;
    @FXML
    RadioButton players4;

    // Reference to the main application.
    private Main main;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public WelcomeScreenController() {
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
        int players;
        if(players2.isSelected()) players = 2;
        else if(players3.isSelected()) players = 3;
        else players = 4;

        main.closeScreen();
        main.showConfigScreens(players);
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
