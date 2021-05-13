package snake;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.LinkedList;

public class Snake {
    private final GameEngine engine;
    private static Snake instance;
    private final LinkedList<Node> snakeChain = new LinkedList<>();
    private Direction direction;
    private int BLOCK_SIZE = 20;

    private Snake(){
        this.engine = GameEngine.getInstance();
        this.direction = Direction.RIGHT;
        var head = new Rectangle(this.BLOCK_SIZE, this.BLOCK_SIZE);
        head.setTranslateY(Utils.rand(this.BLOCK_SIZE*20));
        head.setFill(Color.FORESTGREEN); // head color

        this.snakeChain.add(head);
    }

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

    public int getBlockSize(){
        return this.BLOCK_SIZE;
    }

    public Node getHead(){
        return this.snakeChain.peek();
    }

    public LinkedList<Node> getSnakeChain(){
        return this.snakeChain;
    }

    public int getLength(){
        return this.snakeChain.size();
    }
}
