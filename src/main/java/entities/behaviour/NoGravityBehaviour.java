package entities.behaviour;

import entities.AbstractEntity;

/**
 * Behaviour for entities that fall.
 */
public class NoGravityBehaviour extends AbstractPhysicsBehaviour {
    /**
     * Constructs a new NoGravityBehaviour instance.
     *
     * @param entity the entity to apply physics on.
     */
    public NoGravityBehaviour(final AbstractEntity entity) {
        super(entity);
    }

    @Override
    public void applyPhysics(double timeDifference) {
        // Does nothing...
    }
}
