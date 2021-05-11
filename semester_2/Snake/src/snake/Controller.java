package snake;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class Controller {
    @FXML
    private void keyHandler(KeyEvent event) {
        var keyCode = event.getCode();
        if (keyCode == KeyCode.UP){
            //go up
            return;
        }

        if (keyCode == KeyCode.RIGHT){
            return;
        }

        if (keyCode == KeyCode.DOWN){
            return;
        }

        if (keyCode == KeyCode.LEFT){
            return;
        }
    }
}
