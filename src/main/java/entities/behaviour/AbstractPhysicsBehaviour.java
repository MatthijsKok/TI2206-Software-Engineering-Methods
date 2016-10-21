package entities.behaviour;

import entities.AbstractEntity;

/**
 * Interface for all physics behaviours.
 */
public abstract class AbstractPhysicsBehaviour {

    /**
     * The entity this behaviour works on.
     */
    private final AbstractEntity entity;

    /**
     * Constructs a new AbstractPhysicsBehaviour instance.
     * @param entity the entity to apply physics on.
     */
    AbstractPhysicsBehaviour(final AbstractEntity entity) {
        this.entity = entity;
    }

    /**
     * Applies physics on a entity.
     * @param timeDifference the amount of time that passed.
     */
    public void applyPhysics(final double timeDifference) {
        // To be overridden by sub classes.
    }

    /**
     * @return the entity this physics works on.
     */
    final /* default */ AbstractEntity getEntity() {
        return entity;
    }
}
