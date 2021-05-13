package snake;

import java.awt.*;
import java.util.LinkedList;

public class Snake {
    private static Snake instance;
    private LinkedList<Point> snake;

    private Snake(){ }

    public static Snake getInstance(){
        if (Snake.instance == null){
            Snake.instance = new Snake();
        }

        return Snake.instance;
    }

    public static void removeInstance(){
        Snake.instance = null;
    }

    public void setDirection(Direction newDirection){
        System.out.println(newDirection);
        return;
    }

    private Point head() {
        return this.snake.getFirst();
    }

    private Point tail(){
        return this.snake.getLast();
    }
}
