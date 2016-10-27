package entities.blocks;

import com.sun.javafx.geom.Vec2d;
import entities.AbstractEntity;

/**
 * Represents AbstractBlock entity used for different walls in the game.
 */
public abstract class AbstractBlock extends AbstractEntity {

    /**
     * Creates a block at position (x,y).
     * @param position Vec2d - Position of the block
     */
    AbstractBlock(final Vec2d position) {
        super(position);
    }
}
