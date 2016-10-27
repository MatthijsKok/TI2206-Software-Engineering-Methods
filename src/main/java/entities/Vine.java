package entities;

import com.sun.javafx.geom.Vec2d;
import entities.balls.AbstractBall;
import entities.character.Character;
import geometry.Rectangle;
import graphics.Sprite;
import util.sound.SoundEffect;

/**
 * Vine class, controlling the rope in the game.
 */
public class Vine extends AbstractEntity {

    /**
     * Sprite of the vine.
     */
    private static final Sprite VINE_SPRITE = new Sprite("vine.png", new Vec2d(12, 0));

    /**
     * collision shape of the vine. Created around the original sprite.
     */
    private static final Rectangle VINE_SHAPE = new Rectangle(VINE_SPRITE);

    /**
     * Constant upward speed of the vine in px/s.
     */
    private static final double TRAVEL_SPEED = 300; // px/s

    /**
     * Score that is multiplied by the size of the ball, and then added to the score.
     */
    private static final int SCORE_PER_BALL = 100;

    /**
     * The character that shot this vine.
     */
    private final Character character;

    /**
     * Creates a new vine.
     *
     * @param position spawn position of the vine.
     * @param character character which shot the vine.
     */
    public Vine(final Vec2d position, final Character character) {
        super(position);
        setSprite(VINE_SPRITE);
        setShape(new Rectangle(VINE_SHAPE));
        setYSpeed(-TRAVEL_SPEED);
        setDepth(1);
        this.character = character;
    }

    private void die() {
        getLevel().removeEntity(this);
        character.vineRemoved();
        SoundEffect.SHOOT.getAudio().stop();
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
        if (entity instanceof WallBlock) {
            collideWithBlock();
        }
    }

    /**
     * Collision with a ball, the vine should disappear and the score should increase.
     *
     * @param ball the ball this rope collides with
     */
    private void collideWith(final AbstractBall ball) {
        die();

        final int score = (ball.getSize() + 1) * SCORE_PER_BALL;
        character.increaseScore(score);
    }

    /**
     * Collision with a block, the vine should disappear and the score should increase.
     */
    private void collideWithBlock() {
        SoundEffect.SHOOT.getAudio().stop();
        die();
    }
}
