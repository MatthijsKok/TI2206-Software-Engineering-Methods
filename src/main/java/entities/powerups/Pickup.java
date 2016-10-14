package entities.powerups;

import com.sun.javafx.geom.Vec2d;
import entities.Block;
import entities.Character;
import entities.Entity;
import game.Game;
import geometry.Rectangle;
import geometry.Shape;
import level.Level;
import util.Sprite;

/**
 * Pickup that contains a power-up effect that will be applied to the player.
 */
public class Pickup extends Entity {
    
    /**
     * Gravity applied to a power-up, in pixels per second squared.
     */
    private static final double GRAVITY = 300; // px/s^2

    /**
     * The standard time before the pickup despawns in seconds.
     */
    private static final double DESPAWN_TIME = 5;

    /**
     * The bounding box of the pickup.
     */
    private Rectangle shape;

    /**
     * the amount of time remaining before the pick-up the pickup despawns.
     */
    private double timeRemaining = DESPAWN_TIME;

    /**
     * Constructor for Pickup.
     * @param position The position of the powerup.
     */
    public Pickup(Vec2d position) {
        super(position);

        // Create sprite and set its offset to the center.
        Sprite pickUpSprite = new Sprite("pickup.png");
        pickUpSprite.setOffsetToCenter();
        this.setSprite(pickUpSprite);

        // Set the hitbox of the Pickup
        shape = new Rectangle(sprite.getWidth(), sprite.getHeight());
        updatePosition(0);
    }

    /**
     * Update the speed and position of the ball.
     * @param dt Time difference from previous update in seconds.
     */
    public void update(double dt) {
        // Apply gravity
        speed.y += GRAVITY * dt;

        // Move
        updatePosition(dt);

        // Update time
        updateTimeRemaining(dt);

    }

    /**
     * Update the characters position and collision shape.
     * @param dt the time
     */
    private void updatePosition(final double dt) {
        position.x += speed.x * dt;
        position.y += speed.y * dt;
        shape.setPosition(
                position.x - sprite.getOffsetX(),
                position.y - sprite.getOffsetY()
        );
    }

    /**
     * Entry point for all collisions.
     * This method should only change the behaviour of the pickup, not the Entity it is
     * colliding with. The colliding Entity should handle that itself in it's own
     * "collideWith" method.
     * @param entity the Entity this pickup collides with.
     */
    public void collideWith(Entity entity) {
        if (entity instanceof Block) {
            collideWith((Block) entity);
        }
        if (entity instanceof Character) {
            collideWith((Character) entity);
        }
    }

    /**
     * The behaviour of the Ball when it collides with a Block Entity.
     * @param block The Block Entity this Ball collides with.
     */
    private void collideWith(Block block) {
        position.y = Math.min(block.getY() - sprite.getOffsetY(), position.y);
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
     * @return this pickup's shape.
     */
    public Shape getShape() {
        return shape;
    }

}
