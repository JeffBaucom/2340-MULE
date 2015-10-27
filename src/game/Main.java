package game;

import java.io.IOException;

import com.google.gson.Gson;
import game.view.GameScreenController;
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
import java.io.PrintWriter;

public class Main extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    private ScreenStackController screenStack;

    private final String MAIN = "/game/view/MainScreen.fxml";
    private final String PLAYER_CONFIG = "/game/view/PlayerConfig.fxml";
    private final String GAME_SCREEN = "/game/view/GameScreen.fxml";
    private final String MAP = "/game/view/Map.fxml";
    private final String TOWN = "/game/view/Town.fxml";
    private final String STORE = "/game/view/Store.fxml";
    private final String GAME_SAVE = "src/game/resources/save/save.txt";

    private static Main main;
    private GameScreenController gameScreenController;
    private Game game;

    MediaPlayer mediaPlayer;
    String MUSIC_FILE = "src/game/resources/music/background.mp3";

    @Override
    public void start(Stage primaryStage) {
        Media sound = new Media(new File(MUSIC_FILE).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("MULE Game");
        this.primaryStage.getIcons()
                .add(new Image("/game/resources/images/icon.png"));

        main = this;
        screenStack = new ScreenStackController();

        screenStack.loadScreen("main", MAIN);
        screenStack.loadScreen("player config", PLAYER_CONFIG);

        initRootLayout();
        showScreen("main");
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

    public void newGame(int playerCount, int difficulty, String mapType) {
        game = new Game(playerCount, difficulty, mapType);
    }

    public void generateGameScreen() {
        game.startGame();
        game.reorderPlayers();
        mediaPlayer.stop();

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class
                    .getResource(GAME_SCREEN));
            rootLayout.setBottom(loader.load());
            gameScreenController = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(gameScreenController);
        screenStack.loadScreen("map", MAP);
        screenStack.loadScreen("town",  TOWN);
        screenStack.loadScreen("store", STORE);
    }

    public void showScreen(String name) { screenStack.setScreen(name); }

    public void closeScreen() { screenStack.removeTop(); }

    public GameScreenController getGameScreenController() { return
            gameScreenController; }

    public Game getGame() {
        return game;
    }

    public void saveGame() {
        // Gson gson = new Gson();
        // String json = gson.toJson(game);
        //
        // try {
        //  PrintWriter out = new PrintWriter(new File(GAME_SAVE));
        //  out.println(json);
        // } catch(IOException e) {
        //  e.printStackTrace();
        // }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
