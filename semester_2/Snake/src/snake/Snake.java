package snake;

import java.util.LinkedList;

public class Snake {
    private final GameEngine engine;
    private static Snake instance;
    private final LinkedList<SnakeBody> snakeChain = new LinkedList<>();
    private Direction direction;

    private Snake() {
        this.engine = GameEngine.getInstance();
        this.direction = Direction.RIGHT;
        var head = new SnakeBody(7, 10, Direction.RIGHT);
        var body = new SnakeBody(6, 10,Direction.RIGHT);
        var body1 = new SnakeBody(5, 10,Direction.RIGHT);
        this.snakeChain.add(head);
        this.snakeChain.add(body);
        this.snakeChain.add(body1);
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
        var head = this.snakeChain.getFirst();
        this.move(head, this.direction);

        for (int i = 1; i < this.snakeChain.size(); i++) {
            var next = this.snakeChain.get(i-1);
            var cur = this.snakeChain.get(i);

            if (this.direction == Direction.UP){
                System.out.println(111);
            }

            cur = this.move(cur, next.lastDirection);
        }

        this.ensureFoodAte();
        this.ensureNotAteItself();
    }

    private SnakeBody move(SnakeBody body, Direction direction) {
        var lastDirection = body.direction;
        switch (direction) {
            case UP:
                body.y--;
                body.direction = Direction.UP;
                break;
            case DOWN:
                body.y++;
                body.direction = Direction.DOWN;
                break;
            case LEFT:
                body.x--;
                body.direction = Direction.LEFT;
                break;
            case RIGHT:
                body.x++;
                body.direction = Direction.RIGHT;
                break;
        }
        body.lastDirection = lastDirection;

        this.ensurePositionOnField(body);

        return body;
    }

    public SnakeBody getHead() {
        return this.snakeChain.peek();
    }

    public LinkedList<SnakeBody> getSnakeChain() {
        return this.snakeChain;
    }

    private void ensurePositionOnField(SnakeBody body) {
        if (body.x >= GameProperties.SnakeFieldWidth / GameProperties.SnakeFieldSquareSize) {
            body.x = 0;
            return;
        }

        if (body.x < 0) {
            body.x = GameProperties.SnakeFieldWidth / GameProperties.SnakeFieldSquareSize - 1;
            return;
        }

        if (body.y >= GameProperties.SnakeFieldHeight / GameProperties.SnakeFieldSquareSize) {
            body.y = 0;
            return;
        }

        if (body.y < 0) {
            body.y = GameProperties.SnakeFieldHeight / GameProperties.SnakeFieldSquareSize - 1;
            return;
        }
    }

    private void ensureNotAteItself(){
        var head = this.getHead();

        var flag = this.snakeChain
                                .stream()
                                .skip(1)
                                .anyMatch(e -> Utils.isCrossing(head, e));

        if (flag) {
            this.engine.ateItself();
        }
    }

    private void ensureFoodAte() {
        var food = this.engine.getFood();
        var head = this.getHead();

        for (var elem : food) {
            if (elem.getX() == head.x && elem.getY() == head.y) {
                this.chooseBehavior(elem);
                food.remove(elem);
                this.engine.wasEaten(elem);
                return;
            }
        }
    }

    private void chooseBehavior(Food food) {
        var effect = food.getEffect();
        switch (effect) {
            case Grow -> this.grow();
            case SpeedUp -> this.engine.speedUp();
        }
    }

    public void grow() {
        var tail = tail();
        var newTail = new SnakeBody(tail);

        this.snakeChain.add(newTail);
    }

    private SnakeBody tail() {
        return this.snakeChain.getLast();
    }
}
