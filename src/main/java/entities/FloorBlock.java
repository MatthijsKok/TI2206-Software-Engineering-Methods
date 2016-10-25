package entities;

import com.sun.javafx.geom.Vec2d;
import graphics.Sprite;

/**
 * Represents a FloorBlock entity used for floors in the game.
 */
public class FloorBlock extends AbstractBlock {

    /**
     * The sprite used for the block object in the game.
     */
    private static final Sprite BLOCK_SPRITE = new Sprite("grass_block.png");

    /**
     * Creates a new FloorBlock at position (x,y).
     * @param position the position of the block
     */
    public FloorBlock(final Vec2d position) {
        super(position);
        setSprite(BLOCK_SPRITE);
        setShapeToSprite();
    }

    /**
     * @return The sprite of the block
     */
    public static Sprite getBlockSprite() {
        return BLOCK_SPRITE;
    }
}
