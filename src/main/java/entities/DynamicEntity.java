package entities;

/**
 * Dynamic entities are updated each frame.
 */
public interface DynamicEntity {
    /**
     * Updates the entity's state.
     * @param timeDifference The time elapsed since the last time
     *                       the method was called
     */
    void update(double timeDifference);
}
