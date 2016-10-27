package entities.character.bullets;

import com.sun.javafx.geom.Vec2d;
import entities.AbstractEntity;
import entities.CollidingEntity;
import entities.DynamicEntity;
import entities.balls.AbstractBall;
import entities.blocks.WallBlock;
import entities.character.Character;
import entities.character.Gun;
import util.sound.SoundEffect;

/**
 * The base class for all kinds of bullets.
 */
public abstract class AbstractBullet extends AbstractEntity implements DynamicEntity, CollidingEntity {

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
    AbstractBullet(final Vec2d position, final Character character) {
        super(position);
        setDepth(1);
        this.character = character;
    }

    @Override
    public void update(final double timeDifference) {
        if (getY() <= 0) {
            die();
        }
    }

    @Override
    public void collideWith(final AbstractEntity entity) {
        SoundEffect.SHOOT.getAudio().stop();

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
        getCharacter().increaseScore(score);
    }

    /**
     * Collision with a block, the vine should disappear and the score should increase.
     */
    private void collideWithBlock() {
        die();
    }

    /**
     * @return The gun this bullet is shot from.
     */
    /* default */ final Gun getGun() {
        return character.getGun();
    }

    /**
     * @return The character that shot this bullet.
     */
    /* default */ final Character getCharacter() {
        return character;
    }

    /**
     * Called when the bullet dies.
     */
    /* default */ abstract void die();

}
