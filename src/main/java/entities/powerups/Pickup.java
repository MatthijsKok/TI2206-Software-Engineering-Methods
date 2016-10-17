package entities.powerups;

import com.sun.javafx.geom.Vec2d;
import entities.AbstractEntity;
import entities.Character;
import entities.FloorBlock;
import geometry.Rectangle;

/**
 * Pickup that contains a power-up effect that will be applied to the player.
 */
class Pickup extends AbstractEntity {
    /**
     * Gravity applied to a power-up, in pixels per second squared.
     */
    private static final double GRAVITY = 300; // px/s^2

    /**
     * The standard time before the pickup disappears in seconds.
     */
    private static final double LIFE_DURATION = 5;

    /**
     * The amount of time remaining before the pick-up the pickup disappears.
     */
    private double timeRemaining = LIFE_DURATION;

    /**
     * The power-up that this pickup activates.
     */
    private final PowerUp powerUp;

    /**
     * Constructor for Pickup.
     * @param position The position of the power-up.
     * @param powerUp The power-up that the pickup contains.
     */
    Pickup(Vec2d position, PowerUp powerUp) {
        super(position);

        this.powerUp = powerUp;

        setSprite(powerUp.getSprite());
        setShape(new Rectangle(getSprite()));
    }

    /**
     * Update the speed and position of the ball.
     * @param timeDifference Time difference from previous update in seconds.
     */
    public void update(double timeDifference) {
        // Apply gravity
        getSpeed().y += GRAVITY * timeDifference;

        // Update time
        timeRemaining -= timeDifference;

        if (timeRemaining <= 0) {
            getLevel().removeEntity(this);
        }
    }

    /**
     * Entry point for all collisions.
     * This method should only change the behaviour of the pickup, not the Entity it is
     * colliding with. The colliding Entity should handle that itself in it's own
     * "collideWith" method.
     * @param entity the Entity this pickup collides with.
     */
    public void collideWith(AbstractEntity entity) {
        if (entity instanceof FloorBlock) {
            collideWith((FloorBlock) entity);
        }
        if (entity instanceof Character) {
            collideWith((Character) entity);
        }
    }

    /**
     * The behaviour of the Ball when it collides with a Block Entity.
     * @param floor The Block Entity this Ball collides with.
     */
    private void collideWith(FloorBlock floor) {
        ((Rectangle) getShape()).setBottom(((Rectangle) floor.getShape()).getTop());
        getSpeed().y = 0;
    }

    /**
     * The behaviour of the pickup when it collides with a Block Entity.
     * @param character The Character Entity this Ball collides with.
     */
    private void collideWith(Character character) {
        powerUp.setTarget(character);
        getLevel().removeEntity(this);
    }
}
