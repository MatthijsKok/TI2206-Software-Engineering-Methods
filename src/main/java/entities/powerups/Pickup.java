package entities.powerups;

import com.sun.javafx.geom.Vec2d;
import entities.AbstractEntity;
import entities.Character;
import entities.FloorBlock;
import game.Game;
import geometry.Rectangle;
import graphics.Sprite;
import level.Level;

/**
 * Pickup that contains a power-up effect that will be applied to the player.
 */
public class Pickup extends AbstractEntity {

    /**
     * Gravity applied to a power-up, in pixels per second squared.
     */
    private static final double GRAVITY = 300; // px/s^2

    /**
     * The standard time before the pickup despawns in seconds.
     */
    private static final double DESPAWN_TIME = 5;

    /**
     * the amount of time remaining before the pick-up the pickup despawns.
     */
    private double timeRemaining = DESPAWN_TIME;

    /**
     * The power-up that this pickup activates.
     */
    private PowerUp powerUp;

    /**
     * Constructor for Pickup.
     * @param position The position of the powerup.
     * @param powerUp The powerUp that the pickup contains.
     */
    public Pickup(Vec2d position, PowerUp powerUp) {
        super(position);

        // Create sprite and set its offset to the center.
        Sprite pickUpSprite = new Sprite("pickup.png");
        pickUpSprite.setOffsetToCenter();
        this.setSprite(pickUpSprite);
        this.powerUp = powerUp;

        setShape(new Rectangle(pickUpSprite));
    }

    /**
     * Update the speed and position of the ball.
     * @param dt Time difference from previous update in seconds.
     */
    public void update(double dt) {
        // Apply gravity
        getSpeed().y += GRAVITY * dt;

        // Move
        updatePosition(dt);

        // Update time
        updateTimeRemaining(dt);

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
     * @param block The Block Entity this Ball collides with.
     */
    private void collideWith(FloorBlock block) {
        getPosition().y = Math.min(block.getY() - getSprite().getOffset().y, getPosition().y);
        updatePosition(0);
    }

    /**
     * The behaviour of the pickup when it collides with a Block Entity.
     * @param character The Character Entity this Ball collides with.
     */
    private void collideWith(Character character) {
        Level level = Game.getInstance().getState().getCurrentLevel();
        level.removeEntity(this);
    }

    /**
     * Updates the remaining time for the pick up and removes the pick up if the time is over.
     * @param dt Time difference since last call.
     */
    public void updateTimeRemaining(double dt) {
        Level level = Game.getInstance().getState().getCurrentLevel();

        timeRemaining = timeRemaining - dt;
        if (timeRemaining <= 0) {
            level.removeEntity(this);
        }
    }

    /**
     * @return The power-up this pickup contains.
     */
    public PowerUp getPowerUp() {
        return powerUp;
    }

    /**
     * Sets the power-up of the pickup.
     * @param powerUp The power-up you want the pickup to contain.
     */
    public void setPowerUp(PowerUp powerUp) {
        this.powerUp = powerUp;
    }
}
