package entities;

import com.sun.javafx.geom.Vec2d;
import entities.balls.AbstractBall;
import geometry.Rectangle;
import graphics.Sprite;

/**
 * Vine class, controlling the rope in the game.
 */
public class Vine extends AbstractEntity {

    /**
     * Sprite of the rope.
     */
    private static final Sprite HARPOON_SPRITE = new Sprite("vine.png", new Vec2d(5, 0));

    /**
     * collision shape of the rope. Created around the original sprite.
     */
    private static final Rectangle HARPOON_SHAPE = new Rectangle(HARPOON_SPRITE);

    /**
     * Constant upward speed of the rope in px/s.
     */
    private static final double TRAVEL_SPEED = 250; // px/s

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
    Vine(final Vec2d position, final Character character) {
        super(position);
        setSprite(HARPOON_SPRITE);
        setShape(new Rectangle(HARPOON_SHAPE));
        setYSpeed(-TRAVEL_SPEED);
        setDepth(1);

        this.character = character;
    }

    private void die() {
        getLevel().removeEntity(this);
        character.harpoonRemoved();
    }

    @Override
    public void update(final double timeDifference) {
        if (getY() <= 0) {
            die();
        }
    }

    @Override
    public void collideWith(final AbstractEntity entity) {
        if (entity instanceof AbstractBall) {
            collideWith((AbstractBall) entity);
        }
    }

    /**
     * Collision with a ball, the rope should disappear and the score should increase.
     *
     * @param ball the ball this rope collides with
     */
    private void collideWith(final AbstractBall ball) {
        die();

        final int score = (ball.getSize() + 1) * SCORE_PER_BALL;
        character.increaseScore(score);
    }
}
