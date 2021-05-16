package snake;

public interface SnakeEventListener {
    void wasEaten(Food food);

    void wasGrew();

    void ateItself();

    void speedUp();
}
