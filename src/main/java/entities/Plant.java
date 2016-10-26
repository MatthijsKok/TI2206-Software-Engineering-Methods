package entities;

import com.sun.javafx.geom.Vec2d;
import graphics.Sprite;

/**
 * Plant, the mid level gate.
 */
public class Plant extends AbstractBlock {

    /**
     * The sprite used for the block object in the game.
     */
    private static final Sprite BLOCK_SPRITE = new Sprite("plant.png");

    /**
     * Creates a new FloorBlock at position (x,y).
     * @param position the position of the block
     */
    public Plant(final Vec2d position) {
        super(position);
        setSprite(BLOCK_SPRITE);
        setShapeToSprite();
    }
}
