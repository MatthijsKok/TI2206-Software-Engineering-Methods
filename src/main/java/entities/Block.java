package entities;

import com.sun.javafx.geom.Vec2d;

/**
 * Represents Block entity used for different walls in the game.
 */
public abstract class Block extends AbstractEntity {

    /**
     * Creates a wall on position (x,y).
     * @param position the position of the wall
     */
    public Block(final Vec2d position) {
        super(position);
    }
}
