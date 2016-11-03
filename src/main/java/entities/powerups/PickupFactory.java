package entities.powerups;

import com.sun.javafx.geom.Vec2d;
import level.Level;
import util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory class which creates power-up pickups.
 */
public final class PickupFactory {

    /**
     * HashMap containing the relative probabilities of power-up spawn chances.
     */
    private static List<Pair<Integer, Class>> powerUpProbabilities = new ArrayList<>();

    static {
        powerUpProbabilities.add(new Pair<>(1, ExtraLife.class));
        powerUpProbabilities.add(new Pair<>(2, ExtraVine.class));
        powerUpProbabilities.add(new Pair<>(2, ExtraTime.class));
        powerUpProbabilities.add(new Pair<>(2, SpeedBoost.class));
        powerUpProbabilities.add(new Pair<>(2, ActivateShield.class));
    }

    /**
     * Empty constructor.
     */
    private PickupFactory() { }

    /**
     * Creates a random power-up according to the probabilities that
     * Power-ups are spawned.
     * @return the new power-up.
     */
    private static AbstractPowerUp createRandomPowerUp() {
        int totalProbability = powerUpProbabilities.stream()
                .map(Pair::getKey)
                .reduce(0, (a, b) -> a + b);
        double random = Math.random() * totalProbability;

        int p = 0, i = 0;
        while ((p = p + powerUpProbabilities.get(i).getKey()) < random) {
            i++;
        }

        try {
            return (AbstractPowerUp) powerUpProbabilities.get(i).getValue().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            return null;
        }
    }

    /**
     * Creates a new PickUp instance at the specified position with
     * a randomly chosen Power-up.
     * @param position Vec2d spawn position of this pickup.
     */
    private static Pickup createPickUp(AbstractPowerUp powerUp, Vec2d position) {
        return new Pickup(position, powerUp);
    }

    /**
     * Creates a new Pickup and adds it to a level.
     * @param level    the level the pickup is created in.
     * @param position the spawn position of this pickup.
     */
    public static void spawnRandomPickUp(Level level, Vec2d position) {
        AbstractPowerUp powerUp = createRandomPowerUp();
        level.addEntity(createPickUp(powerUp, position));
    }
}
