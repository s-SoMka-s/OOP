package snake;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;


public class Main extends Application {
    private GameEngine engine;

    @Override
    public void start(Stage stage) throws IOException {
        this.engine = GameEngine.getInstance();
        this.engine.configure(stage);
        this.engine.start();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
