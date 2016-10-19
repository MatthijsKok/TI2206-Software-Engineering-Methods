package entities;

import com.sun.javafx.geom.Vec2d;
import entities.balls.AbstractBall;
import geometry.Rectangle;
import graphics.Sprite;

/**
 * Harpoon entity class.
 */
public class Harpoon extends AbstractEntity {

    /**
     * Sprite of the harpoon.
     */
    private static final Sprite HARPOON_SPRITE = new Sprite("harpoon.png", new Vec2d(5, 0));
    /**
     * Collision shape of the harpoon. Created around the original sprite.
     */
    private static final Rectangle HARPOON_SHAPE = new Rectangle(HARPOON_SPRITE);
    /**
     * Scale of the harpoon sprite.
     */
    private static final double HARPOON_SCALE = 0.5;
    /**
     * Constant upward speed of the harpoon in px/s.
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
        setYSpeed(-TRAVEL_SPEED);
        setScale(HARPOON_SCALE);

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
     * Collision with a ball, the harpoon should disappear and the score should increase.
     *
     * @param ball the ball this harpoon collides with
     */
    private void collideWith(final AbstractBall ball) {
        die();

        final int score = (ball.getSize() + 1) * SCORE_PER_BALL;
        character.increaseScore(score);
    }
}
