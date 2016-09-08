package entities;

import com.sun.javafx.geom.Vec2f;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import util.Sprite;

/**
 * Created by wouterraateland on 07-09-16.
 */
public class Block extends Entity {
    private static Sprite SPRITE = new Sprite("block.png");

    public Block() {
        this(0, 0);
    }

    public Block(double x, double y) {
        super(x, y);
        sprite = Block.SPRITE;
    }
}
