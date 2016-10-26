package level;

import entities.AbstractEntity;
import entities.CollidingEntity;

import java.util.List;

/**
 * Class used for handling collision between entities.
 */
final class CollisionManager {
    /**
     * Entities that entities which need collision handling may
     * collide with.
     */
    private List<AbstractEntity> entities;

    /**
     * Updates the entities that this manager manages.
     * @param entities List<AbstractEntity> the entities to manage.
     */
    /* default */ void setEntities(List<AbstractEntity> entities) {
        this.entities = entities;
    }

    /**
     * Handles collisions between this managers entities.
     */
    /* default */ void handleCollisions() {
        int n = entities.size();
        AbstractEntity a, b;

        for (int i = 0; i < n; i++) {
            a = entities.get(i);

            for (int j = i + 1; j < n; j++) {
                b = entities.get(j);
                if (a.intersects(b)) {
                    if (a instanceof CollidingEntity) {
                        ((CollidingEntity) a).collideWith(b);
                    }

                    if (b instanceof CollidingEntity) {
                        ((CollidingEntity) b).collideWith(a);
                    }
                }
            }
        }
    }
}


