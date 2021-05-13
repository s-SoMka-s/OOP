package snake;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;

public class GameDrawer {
    private final Snake snake = Snake.getInstance();
    private final GameEngine engine = GameEngine.getInstance();
    private GraphicsContext context;

    public GameDrawer(Stage stage) throws IOException {
        var scene = new Scene(createGameScene());
        this.setKeyHandler(scene);
        stage.setScene(scene);
        stage.show();
    }

    public void startAnimation() {
        var timeline = new Timeline(new KeyFrame(Duration.millis(130), e -> this.engine.run(context)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private Parent createGameScene() throws IOException {
        var loader = new FXMLLoader(getClass().getResource("level.fxml"));
        var root = (Pane) loader.load();
        var snakeField = (Pane) root.getChildren().get(0);
        var canvas = new Canvas(GameProperties.SnakeFieldWidth, GameProperties.SnakeFieldHeight);

        snakeField.getChildren().add(canvas);
        this.context = canvas.getGraphicsContext2D();

        return root;
    }

    public void drawBackground() {
        this.context.setFill(Color.web("AAD751"));

        for (int y = 0; y < GameProperties.SnakeFieldHeight; y += 20) {
            for (int x = 0; x < GameProperties.SnakeFieldWidth; x += 20) {
                this.context.fillRect(y, x, GameProperties.SnakeFieldSquareSize, GameProperties.SnakeFieldSquareSize);
            }
        }
    }

    public void drawSnake() {
        this.drawHead();
        var snakeBody = this.snake.getSnakeChain();
        this.drawBody(snakeBody);
    }

    private void drawHead() {
        var head = this.snake.getHead();
        this.context.setFill(Color.web("4674E9"));
        var x = head.getX() * GameProperties.SnakeFieldSquareSize;
        var y = head.getY() * GameProperties.SnakeFieldSquareSize;

        var width = GameProperties.SnakeFieldSquareSize;
        var height = GameProperties.SnakeFieldSquareSize;
        this.context.fillRoundRect(x, y, width, height, 35, 35);
    }

    private void drawBody(LinkedList<Point> snakeBody) {
        for (var elem : snakeBody.subList(1, snakeBody.size())) {
            var x = elem.getX() * GameProperties.SnakeFieldSquareSize;
            var y = elem.getY() * GameProperties.SnakeFieldSquareSize;

            var width = GameProperties.SnakeFieldSquareSize;
            var height = GameProperties.SnakeFieldSquareSize;
            this.context.fillRoundRect(x, y, width,
                    height, GameProperties.SnakeFieldSquareSize, GameProperties.SnakeFieldSquareSize);
        }
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
