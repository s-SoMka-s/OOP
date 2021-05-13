package snake;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

// Singleton
public class GameEngine {
    private static GameEngine instance;
    private Stage stage;

    private GameEngine() {

    }

    public static GameEngine getInstance() {
        if (GameEngine.instance == null) {
            GameEngine.instance = new GameEngine();
        }

        return GameEngine.instance;
    }

    public void configure(Stage stage) {
        this.stage = stage;
    }

    public void start() throws IOException {
        var menu = new Scene(createMenuScene());
        stage.setScene(menu);
        stage.show();
    }

    public void startNewGame() throws IOException {
        var game = new GameSceneDrawer(this.stage);
    }

    private Parent createMenuScene() throws IOException {
        var loader = new FXMLLoader(getClass().getResource("menu.fxml"));
        var root = (Pane) loader.load();
        return root;
    }
}
