package snake;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class GameSceneDrawer {
    private static final int WIDTH = 800;
    private static final int HEIGHT = WIDTH/2;
    private static final int ROWS = 20;
    private static final int COLUMNS = ROWS;
    private static final int SQUARE_SIZE = WIDTH / ROWS;
    private final Snake snake = Snake.getInstance();

    public GameSceneDrawer(Stage stage) throws IOException {
        var scene = new Scene(createGameScene());
        this.setKeyHandler(scene);
        stage.setScene(scene);
        stage.show();
    }

    private Parent createGameScene() throws IOException {
        var loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        var root = (GridPane)loader.load();
        var canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);
        return root;
    }

    public void setKeyHandler(Scene scene) {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                case W:
                    this.snake.setDirection(Direction.UP);
                    break;
                case DOWN:
                case S:
                    this.snake.setDirection(Direction.DOWN);
                    break;
                case RIGHT:
                case D:
                    this.snake.setDirection(Direction.RIGHT);
                    break;
                case LEFT:
                case A:
                    this.snake.setDirection(Direction.LEFT);
                    break;
                case ESCAPE:
                    break;
            }
        });
    }
}
