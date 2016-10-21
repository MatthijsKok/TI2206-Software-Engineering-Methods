package entities;

import com.sun.javafx.geom.Vec2d;
import graphics.Sprite;

/**
 * Represents a FloorBlock entity used for walls in the game.
 */
public class WallBlock extends Block {

    /**
     * The sprite of the wall.
     */
    private static final Sprite WALL_SPRITE = new Sprite("wall.png");

    /**
     * Creates a wall on position (x,y).
     * @param position the position of the wall
     */
    public WallBlock(final Vec2d position) {
        super(position);
        setSprite(WALL_SPRITE);
        setShapeToSprite();
    }
}
