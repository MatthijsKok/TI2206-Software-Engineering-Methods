package entities;

import com.sun.javafx.geom.Vec2d;
import geometry.Rectangle;
import graphics.Sprite;

/**
 * Represents a FloorBlock entity used for floors in the game.
 */
class FloorBlock extends AbstractEntity {

    /**
     * The sprite used for the block object in the game.
     */
    private static final Sprite BLOCK_SPRITE = new Sprite("block.png");
    /**
     * The shape used for the block object in the game.
     */
    private static final Rectangle BLOCK_SHAPE = new Rectangle(BLOCK_SPRITE);

    /**
     * Creates a new FloorBlock at position (x,y).
     * @param position the position of the block
     */
    FloorBlock(final Vec2d position) {
        super(position);
        setSprite(BLOCK_SPRITE);
        setShape(new Rectangle(BLOCK_SHAPE));
    }
}
