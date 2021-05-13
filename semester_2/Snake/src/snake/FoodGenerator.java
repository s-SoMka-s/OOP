package snake;


import javafx.scene.image.Image;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;

public class FoodGenerator {
    private final Snake snake = Snake.getInstance();


    public FoodGenerator(){

    }

    public Food generate(){
        var cords = this.getCords();
        var path = "src\\snake\\ic_orange.png";
        Image image = null;
        try {
            image = new Image(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        var food = new Food(FoodEffect.Grow, cords.x, cords.y, image);

        return food;
    }

    private Point getCords(){
        int x,y;
        start:
        while (true) {
            x = (int) (Math.random() * GameProperties.SnakeFieldRows);
            y = (int) (Math.random() * GameProperties.SnakeFieldColumns);
            if (this.snake.isCrossed(x, y)) {
                continue start;
            }

            break;
        }

        return new Point(x,y);
    }
}
