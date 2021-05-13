package snake;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Snake {
    private final GameEngine engine;
    private static Snake instance;
    private final LinkedList<Point> snakeChain = new LinkedList<>();
    private Direction direction;
    private int BLOCK_SIZE = 20;

    private Snake() {
        this.engine = GameEngine.getInstance();
        this.direction = Direction.RIGHT;
        var head = new Point(5, 10);

        this.snakeChain.add(head);
    }

    public static Snake getInstance() {
        if (Snake.instance == null) {
            Snake.instance = new Snake();
        }

        return Snake.instance;
    }

    public static void removeInstance() {
        Snake.instance = null;
    }

    public void setDirection(Direction newDirection) {
        this.direction = newDirection;
        return;
    }

    public void move() {
        switch (this.direction) {
            case UP:
                this.snakeChain.getFirst().y--;
                break;
            case DOWN:
                this.snakeChain.getFirst().y++;
                break;
            case LEFT:
                this.snakeChain.getFirst().x--;
                break;
            case RIGHT:
                this.snakeChain.getFirst().x++;
                break;
        }

        this.ensureHeadPositionValid();
    }

    public int getBlockSize() {
        return this.BLOCK_SIZE;
    }

    public Point getHead() {
        return this.snakeChain.peek();
    }

    public LinkedList<Point> getSnakeChain() {
        return this.snakeChain;
    }

    public int getLength() {
        return this.snakeChain.size();
    }

    private void ensureHeadPositionValid(){
        var head = this.getHead();
        if (head.x >= GameProperties.SnakeFieldWidth / GameProperties.SnakeFieldSquareSize){
            head.x = 0;
            return;
        }

        if (head.x < 0) {
            head.x = GameProperties.SnakeFieldWidth / GameProperties.SnakeFieldSquareSize - 1;
            return;
        }

        if (head.y >= GameProperties.SnakeFieldHeight / GameProperties.SnakeFieldSquareSize){
            head.y = 0;
            return;
        }

        if (head.y < 0) {
            head.y = GameProperties.SnakeFieldHeight / GameProperties.SnakeFieldSquareSize - 1;
            return;
        }

        this.wasEaten();
    }

    private void wasEaten(){
        var food = this.engine.getFood();
        var head = this.getHead();

        for (var elem : food){
            if (elem.getX() == head.x && elem.getY() == head.y) {
                food.remove(elem);
                this.engine.foodWasEaten();
                return;
            }
        }
    }

    public boolean isCrossed(int x, int y) {
        for (var elem : this.snakeChain) {
            if (elem.getX() == x && elem.getY() == y) {
                return true;
            }
        }
        return false;
    }

    public void grow(){
        // Need to implement normal grow logic
        // Now new elem places on tail position

        var tail = this.tail();
        var elem = new Point();

        this.snakeChain.add(elem);
    }

    private Point tail(){
        return this.snakeChain.getLast();
    }
}
