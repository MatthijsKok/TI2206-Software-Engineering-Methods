package entities;

import com.sun.javafx.geom.Vec2d;
import geometry.Rectangle;
import graphics.Sprite;

/**
 * Represents a FloorBlock entity used for walls in the game.
 */
public class WallBlock extends AbstractEntity {

    /**
     * The sprite of the wall.
     */
    private static final Sprite WALL_SPRITE = new Sprite("wall.png");
    /**
     * The shape used for the wall object in the game.
     */
    private static final Rectangle WALL_SHAPE = new Rectangle(WALL_SPRITE);

    /**
     * Creates a wall on position (x,y).
     * @param position the position of the wall
     */
    public WallBlock(final Vec2d position) {
        super(position);
        setSprite(WALL_SPRITE);
        setShape(new Rectangle(WALL_SHAPE));
    }
}
