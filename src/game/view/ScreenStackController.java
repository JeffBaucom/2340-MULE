package game.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ScreenStackController extends StackPane {
    private Map<String, Node> screens = new HashMap<String, Node>();
    private Map<String, Controller> controllers = new HashMap<String, Controller>();

    /** Add screen and controller to relevant HashMaps
     */
    public void addScreen(String name, Node screen, Controller controller) {
        screens.put(name, screen);
        controllers.put(name, controller);
    }

    /** Load screen from fxml file
     */
    public void loadScreen(String name, String resource) {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource(resource));
            Parent screen = loader.load();

            // Add screen and controller to stack.
            addScreen(name, screen, loader.getController());
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
            controllers.get(name).update();
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
}
