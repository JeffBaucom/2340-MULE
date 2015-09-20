package game;

import java.io.IOException;

import game.control.ScreenStackController;
import game.model.Game;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

    private ScreenStackController screenStack;

    private final String MAIN = "/game/view/MainScreen.fxml";
    private final String PLAYER_CONFIG = "/game/view/PlayerConfig.fxml";

    private Game game;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("MULE Game");
        this.primaryStage.getIcons().add(new Image
                ("file:resources/images/mule-icon.png"));

        screenStack = new ScreenStackController();
        screenStack.setMain(this);

        screenStack.loadScreen("main", MAIN);
        screenStack.loadScreen("player config", PLAYER_CONFIG);

        initRootLayout();
        showMainScreen();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/game/view/RootLayout.fxml"));
            rootLayout = loader.load();
            rootLayout.setCenter(screenStack);

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void newGame(int playerCount) {
        game = new Game(playerCount);
    }

    /**
     * Shows the game screen in the root layout.
     */
    public void showMainScreen() {
        screenStack.setScreen("main");
    }

    public void showConfigScreens() {
        screenStack.loadScreen("player config", PLAYER_CONFIG);
        screenStack.setScreen("player config");
    }

    public void closeScreen() {
        screenStack.removeTop();
    }

    public Game getGame() {
        return game;
    }

    /**
     * Returns the main stage.
     * @return null
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
