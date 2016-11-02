package level;

import entities.AbstractEntity;
import entities.DynamicEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Manages the entities for a level.
 */
class EntityManager {

    /**
     * The entities currently active in the level.
     */
    private List<AbstractEntity> entities = new ArrayList<>();
    /**
     * The entities that will be removed from the level after the update cycle.
     */
    private List<AbstractEntity> entitiesToRemove = new ArrayList<>();
    /**
     * The entities that will be added to the level after the update cycle.
     */
    private List<AbstractEntity> entitiesToAdd = new ArrayList<>();
    /**
     * The entities which should be updated each frame.
     */
    private List<DynamicEntity> dynamicEntities = new ArrayList<>();
    /**
     * Indicates whether the entities must be sorted.
     */
    private boolean mustSort;

    /**
     * @return a list of all entities in this manager.
     */
    /* default */ final List<AbstractEntity> getEntities() {
        List<AbstractEntity> entities = new ArrayList<>();

        entities.addAll(this.entities);
        entities.addAll(entitiesToAdd);
        entities.removeAll(entitiesToRemove);

        return entities;
    }

    /**
     * Register that an entity has to be added.
     * @param e entity to add
     */
    /* default */ final void addEntity(final AbstractEntity e) {
        entitiesToAdd.add(e);
    }

    /**
     * Register that an entity has to be removed.
     * @param e The entity to remove
     * @return true if e is not already removed, false otherwise
     */
    /* default */ final boolean removeEntity(final AbstractEntity e) {
        if (entities.contains(e) && !entitiesToRemove.contains(e)) {
            entitiesToRemove.add(e);
            return true;
        }

        return false;
    }

    /**
     * Updates all entities in this manager.
     * @param timeDifference time difference between now and last update
     * @return Boolean indicates whether entities have changed.
     */
    /* default */ boolean update(final double timeDifference) {
        for (DynamicEntity entity : dynamicEntities) {
            entity.update(timeDifference);
        }

        for (AbstractEntity entity : entities) {
            entity.applyPhysicsBehaviour(timeDifference);
            entity.updatePosition(timeDifference);
            entity.updateSprite(timeDifference);
        }

        return updateState();
    }

    /**
     * Draws all entities.
     */
    /* default */ void draw() {
        entities.forEach(AbstractEntity::draw);
    }

    /**
     * Indicate that entities must be sorted.
     */
    /* default */ void sort() {
        mustSort = true;
    }

    /**
     * Clears all entities from this manager.
     */
    /* default */ void clear() {
        entities = new ArrayList<>();
        entitiesToRemove = new ArrayList<>();
        entitiesToAdd = new ArrayList<>();
    }

    private boolean updateState() {
        boolean changed = false;

        if (!entitiesToAdd.isEmpty() || !entitiesToRemove.isEmpty()) {
            changed = true;

            removeEntities();
            addEntities();
            dynamicEntities = entities.stream()
                    .filter(entity -> entity instanceof DynamicEntity)
                    .map(entity -> (DynamicEntity) entity)
                    .collect(Collectors.toList());
        }

        if (mustSort) {
            entities.sort((a, b) -> b.getDepth() - a.getDepth());
            mustSort = false;
        }

        return changed;
    }

    /**
     * Really removes all entities that need to be removed from the entity list.
     */
    private void removeEntities() {
        entities.removeAll(entitiesToRemove);
        entitiesToRemove = new ArrayList<>();
    }

    /**
     * Really add all entities that need to be removed to the entity list.
     */
    private void addEntities() {
        entities.addAll(entitiesToAdd);
        entitiesToAdd = new ArrayList<>();
    }
}
