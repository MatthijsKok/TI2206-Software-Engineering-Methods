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
    public abstract void applyPhysics(double timeDifference);

    /**
     * @return the entity this physics works on.
     */
    protected final AbstractEntity getEntity() {
        return entity;
    }
}
