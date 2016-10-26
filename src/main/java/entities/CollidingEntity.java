package entities;

/**
 * Interface for entities that can collide with each other.
 */
public interface CollidingEntity {
    /**
     * Handles the collision between a colliding entity and
     * another entity.
     * @param entity AbstractEntity - entity that the colliding
     *               entity collides with.
     */
    void collideWith(AbstractEntity entity);
}
