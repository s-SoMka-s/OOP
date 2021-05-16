package snake;


import javafx.scene.image.Image;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

public class FoodGenerator {
    private final Snake snake = Snake.getInstance();


    public FoodGenerator(){

    }

    public Food generate(){
        var cords = this.getCords();
        var effect = this.getRandomEffect();
        var path = this.getFoodImg(effect);
        Image image = null;
        try {
            image = new Image(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        var food = new Food(effect, cords.x, cords.y, image);

        return food;
    }

    private Point getCords(){
        int x,y;
        start:
        while (true) {
            x = (int) (Math.random() * GameProperties.SnakeFieldRows);
            y = (int) (Math.random() * GameProperties.SnakeFieldColumns);
            var foodPoint = new Point(x,y);
            var isCrossing = this.snake
                                          .getSnakeChain()
                                          .stream()
                                          .anyMatch(p -> Utils.isCrossing(p.x, p.y, foodPoint.x,foodPoint.y));

            if (isCrossing) {
                continue start;
            }

            break;
        }

        return new Point(x,y);
    }

    private FoodEffect getRandomEffect() {
        return FoodEffect.values()[new Random().nextInt(FoodEffect.values().length)];
    }

    private String getFoodImg(FoodEffect effect) {
        var res = "";
        switch (effect){
            case Grow -> res = "src\\snake\\ic_orange.png";
            case SpeedUp -> res = "src\\snake\\ic_cherry.png";
        }

        return res;
    }
}
