package util;

import entities.Entity;
import game.Game;

import java.util.List;

/**
 * Class used for handling collision between entities.
 */
public final class CollisionManager {

    private CollisionManager() { }

    /**
     * The current game instance.
     */
    private static final Game GAME = Game.getInstance();

    /**
     * Handles collisions between all entities currently in the level.
     * Both a.collideWith(b) and b.collideWith(a) are called because a
     * only knows what to do with itself and so does b.
     */
    public static void handleCollisions() {
        List<Entity> entities = GAME.getState().getCurrentLevel().getEntities();
        int n = entities.size();
        Entity a, b;
        for (int i = 0; i < n; i++) {
            a = entities.get(i);
            for (int j = i + 1; j < n; j++) {
                b = entities.get(j);
                if (a.intersects(b)) {
                    a.collideWith(b);
                    b.collideWith(a);
                }
            }
        }
    }
}


