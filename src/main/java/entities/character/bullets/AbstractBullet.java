package entities.character.bullets;

import com.sun.javafx.geom.Vec2d;
import entities.AbstractEntity;
import entities.CollidingEntity;
import entities.balls.AbstractBall;
import entities.blocks.SpikeBlock;
import entities.blocks.WallBlock;
import entities.character.Character;
import entities.character.Gun;
import util.sound.SoundEffect;

/**
 * The base class for all kinds of bullets.
 * A character shoots a bullet to kill balls.
 */
public abstract class AbstractBullet extends AbstractEntity implements CollidingEntity {

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
     * @param position  spawn position of the vine.
     * @param character character which shot the vine.
     */
    AbstractBullet(final Vec2d position, final Character character) {
        super(position);
        setDepth(2);
        this.character = character;
    }

    @Override
    public void collideWith(final AbstractEntity entity) {
        SoundEffect.SHOOT.getAudio().stop();

        if (entity instanceof AbstractBall) {
            collideWith((AbstractBall) entity);
        }

        if (entity instanceof WallBlock
                || entity instanceof SpikeBlock) {
            collideWithBlock();
        }
    }

    /**
     * When a bullet collides with a ball, the bullet should disappear and the score should increase.
     * @param ball the ball this rope collides with.
     */
    private void collideWith(final AbstractBall ball) {
        die();

        final int score = (ball.getSize() + 1) * SCORE_PER_BALL;
        getCharacter().increaseScore(score);
    }

    /**
     * When a bullet collides with a block, the vine should disappear and the score should increase.
     */
    private void collideWithBlock() {
        die();
    }

    /**
     * Getter for the gun.
     * @return The gun this bullet is shot from.
     */
    /* default */ final Gun getGun() {
        return character.getGun();
    }

    /**
     * Getter for the character.
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
