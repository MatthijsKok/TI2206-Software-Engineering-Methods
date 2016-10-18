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
     * Returns a boolean indicating if a pickup should be spawned.
     * @return A boolean indicating if a pickup should be spawned.
     */
    private static boolean shouldSpawn() {
        return (Math.random() < SPAWN_CHANCE);
    }

    /**
     * Randomly determines the kind of power-up the pickup should contain.
     */
    private static PowerUp determinePowerUp() {
        List<String> powerUpList = new ArrayList<>();

        powerUpList.add("ExtraLife");
        powerUpList.add("ExtraRope");
        powerUpList.add("ExtraTime");
        powerUpList.add("SpeedBoost");
        powerUpList.add("Shield");

        Long randomNumber = Math.round(Math.random() * powerUpList.size());


        return createPowerUp(randomNumber.intValue());
    }

    /**
     * Returns the correct PowerUp Object from a String.
     * @param randomNumber A random number between 0 and the amount of powerup
     * @return A new PowerUp object.
     */
    @SuppressWarnings("magicnumber")
    private static PowerUp createPowerUp(int randomNumber) {
        switch (randomNumber) {
            case 0:
                return new ExtraLife();
            case 1:
                return new ExtraRope();
            case 2:
                return new ExtraTime();
            case 3:
                return new SpeedBoost();
            case 4:
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
