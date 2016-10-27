package entities.blocks;

import com.sun.javafx.geom.Vec2d;
import graphics.Sprite;

/**
 * Represents a FloorBlock entity used for floors in the game.
 */
public class FloorBlock extends AbstractBlock {

    /**
     * The sprite used for the block object in the game.
     */
    private static final Sprite FLOOR_SPRITE = new Sprite("images/block.png");

    /**
     * Creates a new FloorBlock at position (x,y).
     * @param position Vec2d - Position of the block
     */
    public FloorBlock(final Vec2d position) {
        super(position);
        setSprite(BLOCK_SPRITE);
        setShapeToSprite();
    }
}
