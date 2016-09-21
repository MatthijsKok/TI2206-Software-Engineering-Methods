package entities;

import geometry.Rectangle;
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
     * Creates a wall on position (x,y).
     * @param x the x coordinate of the wall
     * @param y the y coordinate of the wall
     */
    public Wall(double x, double y) {
        super(x, y);
        sprite = Wall.SPRITE;
        shape = new Rectangle(sprite.getWidth(), sprite.getHeight());
        shape.setPosition(x, y);
    }

    /**
     * Returns the x value that is most left on the sprite.
     * @return the x value that is most left on the sprite
     */
    public double getLeft() {
        return getX();
    }

    /**
     * Returns the x value that is most right on the sprite.
     * @return the x value that is most right on the sprite
     */
    public double getRight() {
        return getX() + sprite.getWidth();
    }
}
