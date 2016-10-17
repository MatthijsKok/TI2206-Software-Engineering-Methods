package entities;

import com.sun.javafx.geom.Vec2d;
import geometry.Rectangle;
import graphics.Sprite;

/**
 * Harpoon class, controlling the rope in the game.
 */
public class Harpoon extends AbstractEntity {

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
     * Score that is multiplied by the size of the ball, and then added to the score.
     */
    private static final int SCORE_PER_BALL = 100;

    /**
     * The character that shot this harpoon.
     */
    private final Character character;

    /**
     * Creates a new harpoon.
     *
     * @param position spawn position of the harpoon.
     * @param character character which shot the harpoon.
     */
    Harpoon(final Vec2d position, final Character character) {
        super(position);
        setSprite(HARPOON_SPRITE);
        setShape(new Rectangle(HARPOON_SHAPE));
        setSpeed(0, -TRAVEL_SPEED);

        this.character = character;
    }

    private void die() {
        getLevel().removeEntity(this);
        character.harpoonRemoved();
    }

    /**
     * Updates the state of the harpoon.
     *
     * @param timeDifference delta time
     */
    public void update(final double timeDifference) {
        if (getY() <= 0) {
            die();
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
        die();

        int score = (ball.getSize() + 1) * SCORE_PER_BALL;
        character.increaseScore(score);
    }
}
