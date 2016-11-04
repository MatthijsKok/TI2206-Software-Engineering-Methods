package entities.character.bullets;

import com.sun.javafx.geom.Vec2d;
import entities.CollidingEntity;
import entities.character.Character;
import geometry.Rectangle;
import graphics.Sprite;

/**
 * Vine class, controlling the bullet in the game.
 */
public class Vine extends AbstractBullet implements CollidingEntity {

    /**
     * Sprite of the vine.
     */
    private static final Sprite VINE_SPRITE = new Sprite("images/vine.png", new Vec2d(12, 0));
    /**
     * Collision shape of the vine. Created around the original sprite.
     */
    private static final Rectangle VINE_SHAPE = new Rectangle(VINE_SPRITE);
    /**
     * Constant upward speed of the vine in px/s.
     */
    private static final double TRAVEL_SPEED = 250; // px/s

    /**
     * Creates a new vine.
     * @param position  spawn position of the vine.
     * @param character character which shot the vine.
     */
    public Vine(final Vec2d position, final Character character) {
        super(position, character);
        setSprite(VINE_SPRITE);
        setShape(new Rectangle(VINE_SHAPE));
        setYSpeed(-TRAVEL_SPEED);
    }

    @Override
    /* default */ void die() {
        getLevel().removeEntity(this);
        getGun().bulletDied();
    }
}
