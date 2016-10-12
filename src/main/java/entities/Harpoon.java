package entities;

import com.sun.javafx.geom.Vec2d;
import geometry.Rectangle;
import graphics.Sprite;

/**
 * Harpoon class, controlling the rope in the game.
 */
public final class Harpoon extends AbstractEntity {

    /**
     * Sprite of the rope.
     */
    private static final Sprite HARPOON_SPRITE = new Sprite("rope.png", new Vec2d(5, 0));

    /**
     * collision shape of the rope. Created around the original sprite.
     */
    private static final Rectangle HARPOON_SHAPE = new Rectangle(HARPOON_SPRITE);

    /**
     * Constant upward speed of the rope in px/s.
     */
    private static final double TRAVEL_SPEED = 300; // px/s

    /**
     * Creates a new rope at position (0,0).
     */
    Harpoon() {
        this(new Vec2d(0, 0));
    }

    /**
     * Creates a new rope.
     *
     * @param position position of the rope
     */
    private Harpoon(final Vec2d position) {
        super(position);
        setSprite(HARPOON_SPRITE);
        setShape(new Rectangle(HARPOON_SHAPE));
        setSpeed(0, -TRAVEL_SPEED);
        setVisibility(false);
    }

    /**
     * If the rope is not yet traveling,
     * a rope will spawn at the indicated position.
     *
     * @param position The position where the rope will be
     *                 spawned.
     */
    void shoot(final Vec2d position) {
        if (!isVisible()) {
            setVisibility(true);
            setPosition(position.x, position.y);
        }
    }

    /**
     * Updates the state of the rope.
     *
     * @param timeDifference delta time
     */
    public void update(final double timeDifference) {
        if (getY() <= 0) {
            setVisibility(false);
        }
    }

    /**
     * Entry point for collisions.
     *
     * @param entity the entity this rope collides with
     */
    public void collideWith(final AbstractEntity entity) {
        if (entity instanceof Ball) {
            collideWith((Ball) entity);
        }
    }

    /**
     * Collision with a ball, the rope should disappear and the score should increase.
     *
     * @param ball the ball this rope collides with
     */
    private void collideWith(final Ball ball) {
        if (ball != null) {
            setVisibility(false);
            setChanged();
            notifyObservers(ball);
        }
    }
}
