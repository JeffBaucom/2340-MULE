package game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import game.control.Controller;
import game.control.PlayerConfigController;
import game.control.ScreenStackController;
import game.control.WelcomeScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.RadioButton;

public class Main extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

    private ScreenStackController screenStack;

    private final String WELCOME_SCREEN = "/game/view/WelcomeScreen.fxml";
    private final String PLAYER_CONFIG_SCREEN = "/game/view/PlayerConfig.fxml";

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("MULE Game");
        this.primaryStage.getIcons().add(new Image
                ("file:resources/images/mule-icon.png"));

        screenStack = new ScreenStackController();
        screenStack.setMain(this);

        screenStack.loadScreen("welcome", WELCOME_SCREEN);
        screenStack.loadScreen("player config", PLAYER_CONFIG_SCREEN);

        initRootLayout();
        showWelcomeScreen();
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

    /**
     * Shows the game screen in the root layout.
     */
    public void showWelcomeScreen() {
        screenStack.setScreen("welcome");
    }

    public void showConfigScreens(int players) {
        for (int i = 0; i < players; i++) {
            screenStack.loadScreen("player config" + i, PLAYER_CONFIG_SCREEN);
            screenStack.setScreen("player config" + i);
        }
    }

    public void closeScreen() {
        screenStack.removeTop();
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
