package entities.powerups;

import com.sun.javafx.geom.Vec2d;
import game.Game;
import level.Level;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates power-up pickups.
 */
public final class PickupFactory {

    /**
     * Empty constructor.
     */
    private PickupFactory() { }

    /**
     * A number between 0 and 1, where 0 is 0% chance and 1 is 100% chance of dropping a pickup on a split.
     */
    private static final double SPAWN_CHANCE = 0.5;

    /**
     * Number that incicates how often a power-up spawns in comparison to other powerups.
     */
    private static final int EXTRA_LIFE_WEIGHT = 1;

    /**
     * Number that incicates how often a power-up spawns in comparison to other powerups.
     */
    private static final int EXTRA_ROPE_WEIGHT = 1;

    /**
     * Number that incicates how often a power-up spawns in comparison to other powerups.
     */
    private static final int EXTRA_TIME_WEIGHT = 1;

    /**
     * Number that incicates how often a power-up spawns in comparison to other powerups.
     */
    private static final int SPEEDBOOST_WEIGHT = 1;

    /**
     * Number that incicates how often a power-up spawns in comparison to other powerups.
     */
    private static final int SHIELD_WEIGHT = 1;

    /**
     * Returns a boolean indicating if a pickup should be spawned.
     * @return A boolean indicating if a pickup should be spawned.
     */
    private static boolean shouldSpawn() {
        double randomNumber = Math.random();
        return (randomNumber < SPAWN_CHANCE);
    }

    /**
     * Randomly determines the kind of power-up the pickup should contain.
     */
    private static PowerUp determinePowerUp() {
        List<String> powerUpList = new ArrayList<>();
        List<Integer> powerUpWeights = new ArrayList<>();

        powerUpList.add("ExtraLife");
        powerUpList.add("ExtraRope");
        powerUpList.add("ExtraTime");
        powerUpList.add("SpeedBoost");
        powerUpList.add("Shield");

        powerUpWeights.add(EXTRA_LIFE_WEIGHT);
        powerUpWeights.add(EXTRA_ROPE_WEIGHT);
        powerUpWeights.add(EXTRA_TIME_WEIGHT);
        powerUpWeights.add(SPEEDBOOST_WEIGHT);
        powerUpWeights.add(SHIELD_WEIGHT);

        int totalWeight = 0;

        for (Integer weight : powerUpWeights) {
            totalWeight += weight;
        }

        double randomNumber = Math.random() * totalWeight;

        int i = 0, currentWeight = 0;
        while (i < powerUpWeights.size() - 1 && randomNumber > currentWeight + powerUpWeights.get(i)) {
            i++;
        }
        return createPowerUp(powerUpList.get(i));
    }

    /**
     * Returns the correct PowerUp Object from a String.
     * @param powerUpName The name of the PowerUp
     * @return A new PowerUp object.
     */
    public static PowerUp createPowerUp(String powerUpName) {
        switch (powerUpName) {
            case "ExtraLife":
                return new ExtraLife();
            case "ExtraRope":
                return new ExtraRope();
            case "ExtraTime":
                return new ExtraTime();
            case "SpeedBoost":
                return new SpeedBoost();
            case "Shield":
                return new Shield();
            default: return new ExtraTime();
        }
    }

    /**
     * Randomly spawns a Pickup at the specified position.
     * @param position The position the Pickup should be spawned.
     */
    public static void spawn(Vec2d position) {
        Level level = Game.getInstance().getState().getCurrentLevel();
        if (shouldSpawn()) {
            level.addEntity(new Pickup(position, determinePowerUp()));
        }
    }

}
