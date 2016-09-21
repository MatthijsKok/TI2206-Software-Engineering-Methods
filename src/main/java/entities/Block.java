package entities;

import geometry.Rectangle;
import util.Sprite;

/**
 * Represents a Block entity used for floors and walls and ceilings in the game.
 */
public class Block extends Entity {

    /**
     * The sprite used for the block object in the game.
     */
    private static final Sprite SPRITE = new Sprite("block.png");

    /**
     * Creates a new Block at position (x,y).
     * @param x the x position of the block
     * @param y the y position of the block
     */
    public Block(final double x, final double y) {
        super(x, y);
        sprite = Block.SPRITE;
        shape = new Rectangle(sprite.getWidth(), sprite.getHeight());
        shape.setPosition(x, y);
    }
}
