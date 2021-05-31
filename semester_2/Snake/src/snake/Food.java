package snake;

import javafx.scene.image.Image;

public class Food {
    private final FoodEffect effect;
    private final int x;
    private final int y;
    private final Image image;

    public Food(FoodEffect effect, int x, int y, Image image) {
        this.effect = effect;
        this.x = x;
        this.y = y;
        this.image = image;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public FoodEffect getEffect(){
        return this.effect;
    }

    public Image getImage(){
        return this.image;
    }
}
