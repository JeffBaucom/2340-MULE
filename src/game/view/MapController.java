package game.view;

import javafx.fxml.FXML;

public class MapController extends Controller {

    public MapController() {
    }

    @FXML
    public void initialize() {
    }

    @FXML
    public void enterTown() {
        main.closeScreen();
        main.showTown();
    }
}
