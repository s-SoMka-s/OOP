package snake;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;


public class MainMenuController {
    private final GameEngine engine = GameEngine.getInstance();

    @FXML
    private Button startBtn;

    @FXML
    private void onStartClick(ActionEvent event) throws IOException {
        System.out.println("game starting!");
        this.engine.startNewGame();
    }
}
