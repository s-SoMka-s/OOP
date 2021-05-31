package snake;

public class SnakeBody {
    public int x;
    public int y;
    public Direction lastDirection;
    public Direction direction;

    public SnakeBody(int x, int y, Direction direction){
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public SnakeBody(SnakeBody source){
        this.x = source.x;
        this.y = source.y;
        this.lastDirection = source.lastDirection;
        switch (source.lastDirection) {
            case UP:
                this.y++;
                break;
            case DOWN:
                this.y--;
                break;
            case LEFT:
                this.x++;
                break;
            case RIGHT:
                this.x--;
                break;
        }
    }
}
