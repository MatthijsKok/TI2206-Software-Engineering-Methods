package entities.behaviour;


import entities.AbstractEntity;

/**
 * Behaviour for entities that fall.
 */
public class GravityBehaviour extends AbstractPhysicsBehaviour {

    /**
     * Gravity applied to a power-up, in pixels per second squared.
     */
    private static final double GRAVITY = 300; // px/s^2

    /**
     * Constructs a new GravityBehaviour instance.
     *
     * @param entity the entity to apply physics on.
     */
    public GravityBehaviour(AbstractEntity entity) {
        super(entity);
    }

    @Override
    public void applyPhysics(final double timeDifference) {
        getEntity().setYSpeed(getEntity().getYSpeed() + GRAVITY * timeDifference);
    }
}
