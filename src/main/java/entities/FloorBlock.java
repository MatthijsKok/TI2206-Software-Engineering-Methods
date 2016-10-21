package entities;

import com.sun.javafx.geom.Vec2d;
import graphics.Sprite;

/**
 * Represents a FloorBlock entity used for floors in the game.
 */
public class FloorBlock extends Block {

    /**
     * The sprite used for the block object in the game.
     */
    private static final Sprite BLOCK_SPRITE = new Sprite("block.png");

    /**
     * Creates a new FloorBlock at position (x,y).
     * @param position the position of the block
     */
    public FloorBlock(final Vec2d position) {
        super(position);
        setSprite(BLOCK_SPRITE);
        setShapeToSprite();
    }
}
