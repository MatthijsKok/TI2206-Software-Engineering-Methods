package entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Created by wouterraateland on 07-09-16.
 */
public class Block extends Entity {

    public Block() {
        super();
    }

    public Block(int x, int y) {
        super(x, y);
    }

    private static final Image SPRITE = new Image("block.png");


    public void draw(GraphicsContext gc) {
        gc.drawImage(SPRITE, x - offsetX, y - offsetY);
    }
}
