package entities.powerups;

import com.sun.javafx.geom.Vec2d;
import game.Game;
import level.Level;

/**
 * Creates power-up pickups.
 */
public final class PickupFactory {

    private PickupFactory() { }

    /**
     * A number between 0 and 1, where 0 is no chance and 1 is 100% chance of dropping a pickup on a split.
     */
    private static final double SPAWN_CHANCE = 0.08;

    /**
     * Returns a boolean indicating if a pickup should be spawned.
     * @return A boolean indicating if a pickup should be spawned.
     */
    public static boolean shouldSpawn() {
        double randomNumber = Math.random();
        return (randomNumber < SPAWN_CHANCE);
    }

    /**
     * Randomly spawns a Pickup at the specified position.
     * @param position The position the Pickup should be spawned.
     */
    public static void spawn(Vec2d position) {
        Level level = Game.getInstance().getState().getCurrentLevel();

        if (PickupFactory.shouldSpawn()) {
            level.addEntity(new Pickup(position));
        }
    }
}
