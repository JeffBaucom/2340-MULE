package game;

import java.io.IOException;
import java.util.ArrayList;

import game.control.PlayerConfigController;
import game.control.WelcomeScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
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
    StackPane playerConfigStack;
    private ArrayList<RadioButton> chosenColors;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("MULE Game");

        initRootLayout();
        showWelcomeScreen();
        this.primaryStage.getIcons().add(new Image
                ("file:resources/images/mule-icon.png"));
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
            playerConfigStack = new StackPane();
            chosenColors = new ArrayList<RadioButton>();

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
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource
                    ("/game/view/WelcomeScreen.fxml"));
            AnchorPane personOverview = loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            // Give the controller access to the main app.
            WelcomeScreenController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAllConfigScreens(int players) {
        for (int i = 0; i < players; i++) {
            this.showConfigScreen();
        }
        // Set person overview into the center of root layout.
        rootLayout.setCenter(playerConfigStack);
    }

    public void updatePlayerColors(RadioButton chosen) {
        System.out.println(chosen);
        chosenColors.add(chosen);
    }

    public void showConfigScreen() {
        try {//Load person overview
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource
                    ("/game/view/PlayerConfig.fxml"));
            AnchorPane playerConfigScreen = loader.load();
            playerConfigStack.getChildren().add(playerConfigScreen);

            // Give the controller access to the main app.
            PlayerConfigController controller = loader.getController();
            controller.disableButtons(chosenColors);
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeConfigScreen() {
        playerConfigStack.getChildren().remove(playerConfigStack.getChildren
                ().size() - 1);
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
