package entities;

import geometry.Rectangle;
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
        shape = new Rectangle(sprite.getWidth(), sprite.getHeight());
        shape.setPosition(x, y);
    }
}
