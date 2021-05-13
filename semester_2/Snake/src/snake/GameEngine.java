package snake;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

// Singleton
public class GameEngine implements SnakeEventListener {
    private static GameEngine instance;
    private GameDrawer gameDrawer;
    private FoodGenerator foodGenerator;
    private final ArrayList<Food> food = new ArrayList<>();
    private Stage stage;
    private Snake snake;

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
        this.snake = Snake.getInstance();
        this.foodGenerator = new FoodGenerator();

        var food = this.foodGenerator.generate();
        this.food.add(food);

        this.gameDrawer = new GameDrawer(this.stage);
        this.gameDrawer.startAnimation();
    }

    private Parent createMenuScene() throws IOException {
        var loader = new FXMLLoader(getClass().getResource("menu.fxml"));
        var root = (Pane) loader.load();
        return root;
    }

    public void run(GraphicsContext context) {
        this.gameDrawer.drawBackground();
        this.gameDrawer.drawFood(this.food);
        this.gameDrawer.drawSnake();
        this.snake.move();
    }

    @Override
    public void foodWasEaten() {
        var food = this.foodGenerator.generate();
        this.food.add(food);
    }

    public ArrayList<Food> getFood() {
        return this.food;
    }
}
