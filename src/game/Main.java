package game;

import java.io.IOException;

import game.view.ScreenStackController;
import game.model.Game;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class Main extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

    private ScreenStackController screenStack;

    private final String MAIN = "/game/view/MainScreen.fxml";
    private final String PLAYER_CONFIG = "/game/view/PlayerConfig.fxml";
    private final String MAP = "/game/view/Map.fxml";
    private final String TOWN = "/game/view/Town.fxml";
    private final String STORE = "/game/view/Store.fxml";
    private final String PUB = "/game/view/Pub.fxml";

    private static Main main;
    private Game game;

    @Override
    public void start(Stage primaryStage) {
        String musicFile1 = "src/game/images/background1.mp3";
        Media sound = new Media(new File(musicFile1).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("MULE Game");
        this.primaryStage.getIcons().add(new Image("/game/images/icon.png"));

        main = this;
        screenStack = new ScreenStackController();

        screenStack.loadScreen("main", MAIN);
        screenStack.loadScreen("player config", PLAYER_CONFIG);

        initRootLayout();
        showMainScreen();
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class
                    .getResource("/game/view/RootLayout.fxml"));
            rootLayout = loader.load();
            rootLayout.setCenter(screenStack);

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Main getInstance() { return main; }

    public void newGame(int playerCount, String mapType) {
        game = new Game(playerCount, mapType);

        screenStack.loadScreen("town",  TOWN);
        screenStack.loadScreen("store", STORE);
        screenStack.loadScreen("pub", PUB);
    }

    public void generateMap() {
        game.startGame();
        screenStack.loadScreen("map", MAP);
    }

    public void showMainScreen() {
        screenStack.setScreen("main");
    }

    public void showConfigScreen() {
        screenStack.setScreen("player config");
    }

    public void showMap() {
        screenStack.setScreen("map");
    }

    public void showTown() {
        screenStack.setScreen("town");
    }

    public void showStore() {
        screenStack.setScreen("store");
    }

    public void showPub() {
        screenStack.setScreen("pub");
    }

    public void closeScreen() {
        screenStack.removeTop();
    }

    public Game getGame() {
        return game;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
