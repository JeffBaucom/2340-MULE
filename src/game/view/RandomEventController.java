package game.view;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class RandomEventController extends Controller {
    @FXML
    Text randomEventMessage;

    @FXML
    public void initialize() {
        randomEventMessage.setText(this.main.getGame().getTurn()
                .getRandomEventMessage());
    }
}
