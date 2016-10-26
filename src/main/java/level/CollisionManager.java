package level;

import entities.AbstractEntity;
import entities.CollidingEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class used for handling collision between entities.
 */
final class CollisionManager {

    /**
     * The entities that need collision handling in this level.
     */
    private List<AbstractEntity> collidingEntities;

    /**
     * Entities that entities which need collision handling may
     * collide with.
     */
    private List<AbstractEntity> entities;

    /**
     * Creates a new collision manager.
     */
    CollisionManager() {

    }

    /**
     * Updates the entities that this manager manages.
     * @param entities List<AbstractEntity> the entities to manage.
     */
    void updateEntities(List<AbstractEntity> entities) {
        this.collidingEntities = entities.stream()
                .filter(entity -> entity instanceof CollidingEntity)
                .collect(Collectors.toList());

        this.entities = entities;
    }

    /**
     * Handles collisions between this managers entities.
     */
    void handleCollisions() {
        for (AbstractEntity a : collidingEntities) {
            entities.stream()
                    .filter(a::intersects)
                    .forEach(((CollidingEntity) a)::collideWith);
        }
    }
}


