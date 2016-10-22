package entities.powerups;

import com.sun.javafx.geom.Vec2d;
import entities.AbstractEntity;
import entities.behaviour.GravityBehaviour;
import entities.character.Character;
import entities.FloorBlock;
import geometry.Rectangle;

/**
 * Pickup that contains a power-up effect that will be applied to the player.
 */
class Pickup extends AbstractEntity {
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
    private final AbstractPowerUp powerUp;

    /**
     * Constructor for Pickup.
     * @param position The position of the power-up.
     * @param powerUp The power-up that the pickup contains.
     */
    Pickup(final Vec2d position, final AbstractPowerUp powerUp) {
        super(position);

        this.powerUp = powerUp;

        setSprite(powerUp.getSprite());
        setShape(new Rectangle(getSprite()));
        setPhysicsBehaviour(new GravityBehaviour(this));
    }

    @Override
    public void update(final double timeDifference) {
        // Update time
        timeRemaining -= timeDifference;

        if (timeRemaining <= 0) {
            getLevel().removeEntity(this);
        }
    }

    @Override
    public void collideWith(final AbstractEntity entity) {
        if (entity instanceof FloorBlock) {
            collideWith((FloorBlock) entity);
        }
        if (entity instanceof Character) {
            collideWith((Character) entity);
        }
    }

    /**
     * The behaviour of the AbstractBall when it collides with a AbstractBlock Entity.
     * @param floor The AbstractBlock Entity this AbstractBall collides with.
     */
    private void collideWith(final FloorBlock floor) {
        ((Rectangle) getShape()).setBottom(((Rectangle) floor.getShape()).getTop());
        setYSpeed(0);
    }

    /**
     * The behaviour of the pickup when it collides with a AbstractBlock Entity.
     * @param character The Character Entity this AbstractBall collides with.
     */
    private void collideWith(final Character character) {
        powerUp.setTarget(character);
        getLevel().removeEntity(this);
    }
}
