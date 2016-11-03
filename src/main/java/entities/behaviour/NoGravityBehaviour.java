package entities.behaviour;

import entities.AbstractEntity;

/**
 * Behaviour for entities that don't have GravityBehaviour,
 * like wall, floor, ceiling and vein.
 */
public class NoGravityBehaviour extends AbstractPhysicsBehaviour {

    /**
     * Constructs a new NoGravityBehaviour instance.
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
