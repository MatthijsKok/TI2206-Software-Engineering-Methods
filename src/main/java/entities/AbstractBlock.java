package entities;

import com.sun.javafx.geom.Vec2d;

/**
 * Represents AbstractBlock entity used for different walls in the game.
 */
public abstract class AbstractBlock extends AbstractEntity {

    /**
     * Creates a wall on position (x,y).
     * @param position the position of the wall
     */
    public AbstractBlock(final Vec2d position) {
        super(position);
    }
}
