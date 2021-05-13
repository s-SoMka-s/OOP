package snake;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.LinkedList;

public class GameDrawer {
    private static final int WIDTH = 800;
    private static final int HEIGHT = WIDTH;
    private static final int ROWS = 20;
    private static final int COLUMNS = ROWS;
    private static final int SQUARE_SIZE = WIDTH / ROWS;
    private final Snake snake = Snake.getInstance();
    private final GameEngine engine = GameEngine.getInstance();
    private GraphicsContext context;

    public GameDrawer(Stage stage) throws IOException {
        var scene = new Scene(createGameScene());
        this.setKeyHandler(scene);
        stage.setScene(scene);
        stage.show();
    }

    public void startAnimation(){
        var timeline = new Timeline(new KeyFrame(Duration.millis(130), e -> this.engine.run(context)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private Parent createGameScene() throws IOException {
        var loader = new FXMLLoader(getClass().getResource("level.fxml"));
        var root = (Pane) loader.load();
        var snakeField = (Pane) root.getChildren().get(0);
        var canvas = new Canvas(WIDTH,HEIGHT);

        snakeField.getChildren().add(canvas);
        this.context = canvas.getGraphicsContext2D();

        return root;
    }

    public void drawBackground(){
        this.context.setStroke(Color.DARKGREY);

        for (int y = 0;y<15*20;y+=20){
            for (int x = 0;x<20*20;x+=20){
                this.context.strokeRect(y, x, 20, 20);
            }
        }
    }

    public void drawSnake(){
        this.drawHead();
        var snakeBody = this.snake.getSnakeChain();
        this.drawBody(snakeBody);
    }

    private void drawHead(){
        var head = this.snake.getHead();
        this.context.setFill(Color.web("4674E9"));
        this.context.fillRoundRect(head.getLayoutX() * SQUARE_SIZE, head.getLayoutY() * SQUARE_SIZE, SQUARE_SIZE - 1, SQUARE_SIZE - 1, 35, 35);
    }

    private void drawBody(LinkedList<Node> snakeBody) {
        for (var elem: snakeBody.subList(1, snakeBody.size())) {
            this.context.fillRoundRect(elem.getLayoutX() * SQUARE_SIZE, elem.getLayoutY() * SQUARE_SIZE, SQUARE_SIZE - 1,
                    SQUARE_SIZE - 1, 20, 20);
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
