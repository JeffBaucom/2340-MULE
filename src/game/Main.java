package game;

import java.io.IOException;

import game.view.PlayerConfigController;
import game.view.WelcomeScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

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
            rootLayout = (BorderPane) loader.load();

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
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            // Give the controller access to the main app.
            WelcomeScreenController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showConfigScreen(int players) {
        StackPane playerConfigStack = new StackPane();
        for (int i = 0; i < players; i++) {
            try {// Load person overview.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource
                        ("/game/view/PlayerConfig.fxml"));
                AnchorPane playerConfigScreen = (AnchorPane) loader.load();
                playerConfigStack.getChildren().add(playerConfigScreen);

                // Give the controller access to the main app.
                PlayerConfigController controller = loader.getController();
                controller.setMainApp(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Set person overview into the center of root layout.
        rootLayout.setCenter(playerConfigStack);
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
