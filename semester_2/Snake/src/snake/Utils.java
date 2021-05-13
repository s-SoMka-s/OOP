package snake;

public class Utils {
    public static int rand(int value){
        return (int) (Math.random() * (value - 20)) / 20
                * 20;
    }
}
