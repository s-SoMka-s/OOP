package snake;

import java.awt.*;

public class Utils {
    public static int rand(int value){
        return (int) (Math.random() * (value - 20)) / 20
                * 20;
    }

    public static boolean isCrossing(Point point1, Point point2){
        if (point1.x == point2.x && point1.y == point2.y){
            return true;
        }

        return false;
    }

    public static boolean isCrossing(int x1, int x2, int y1, int y2){
        if (x1 == x2 && y1 ==y2){
            return true;
        }

        return false;
    }

    public static boolean isCrossing(SnakeBody head, SnakeBody e) {
        if (head.x == e.x && head.y == e.y){
            return true;
        }

        return false;
    }
}
