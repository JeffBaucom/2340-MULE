package game.view;

import javafx.fxml.FXML;

public class TownController extends Controller {

    public TownController() {
    }

    @FXML
    public void initialize() {
    }

    @FXML
    public void enterStore() {
        main.closeScreen();
        main.showStore();
    }

    @FXML
    public void returnMap() {
        main.closeScreen();
        main.showMap();
    }

    @FXML
    public void gamblePub() {
        main.getGame().getTurn().gamble();
        main.closeScreen();
        main.showMap();
    }
}
