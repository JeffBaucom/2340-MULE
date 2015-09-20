package game.view;

import game.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.HashMap;

public class ScreenStackController extends StackPane {
    private HashMap<String, Node> screens = new HashMap<String, Node>();
    private Main main;

    /** Add screen to HashMap screens
     */
    public void addScreen(String name, Node screen) {
        screens.put(name, screen);
    }

    /** Load screen from fxml file
     */
    public void loadScreen(String name, String resource) {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource(resource));
            Parent screen = loader.load();

            // Give controller access to main
            Controller controller = loader.getController();
            controller.setMain(main);

            // Add screen to stack.
            addScreen(name, screen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Display screen if possible
     *
     * @return if screen was displayed
     */
    public boolean setScreen(String name) {
        if (screens.get(name) != null) {
            getChildren().add(screens.get(name));
            return true;
        } else  {
            return false;
        }
    }

    public void removeTop() {
        if (getChildren().size() > 0) {
            getChildren().remove(getChildren().size() - 1);
        }
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
