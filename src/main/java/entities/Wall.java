package entities;

import geometry.Rectangle;
import geometry.Shape;
import util.Sprite;

/**
 * Represents a Block entity used for walls in the game.
 */
public class Wall extends Entity {

    /**
     * The sprite of the wall.
     */
    private static final Sprite SPRITE = new Sprite("wall.png");

    /**
     * The shape of the wall.
     */
    private Rectangle shape;

    /**
     * Creates a wall on position (x,y).
     * @param x the x coordinate of the wall
     * @param y the y coordinate of the wall
     */
    public Wall(final double x, final double y) {
        super(x, y);
        sprite = Wall.SPRITE;
        shape = new Rectangle(sprite.getWidth(), sprite.getHeight());
        shape.setPosition(x, y);
    }

    /**
     * @return the collision shape of the wall.
     */
    public final Shape getShape() {
        return shape;
    }

    /**
     * Returns the x value that is most left on the sprite.
     * @return the x value that is most left on the sprite
     */
    public final double getLeft() {
        return shape.getLeft();
    }

    /**
     * Returns the x value that is most right on the sprite.
     * @return the x value that is most right on the sprite
     */
    public final double getRight() {
        return shape.getRight();
    }
}
