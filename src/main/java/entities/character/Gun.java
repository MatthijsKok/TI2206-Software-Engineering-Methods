package entities.character;

import com.sun.javafx.geom.Vec2d;
import entities.AbstractEntity;
import entities.DynamicEntity;
import entities.character.bullets.AbstractBullet;
import util.sound.SoundEffect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Class which represents a gun that is held by a character.
 * @param <T> The type of bullet this gun shoots.
 */
public class Gun<T extends AbstractBullet> extends AbstractEntity implements DynamicEntity {

    /**
     * The character this gun belongs to.
     */
    private final Character character;
    /**
     * The type of bullet this gun shoots.
     */
    private final Class<T> bulletType;
    /**
     * The amount of bullets this character can shoot
     * concurrently.
     */
    private int maxConcurrentShots = 1;
    /**
     * The amount of bullets this character has currently shot.
     */
    private int currentConcurrentShots = 0;
    /**
     * Indicates whether the character is shooting.
     */
    private boolean shooting = false;
    /**
     * Boolean indicating whether the character can shoot.
     */
    private boolean canShoot = true;

    /**
     * Creates a new Gun instance.
     *
     * @param character Character - The character this gun
     *                  belongs to.
     * @param bulletType Class - The type of bullet this gun
     *                   shoots.
     */
    Gun(final Character character, final Class<T> bulletType) {
        super(character.getPosition());
        this.character = character;
        this.bulletType = bulletType;
    }

    @Override
    public void update(final double timeDifference) {
        if (shooting && canShoot && currentConcurrentShots < maxConcurrentShots) {
            currentConcurrentShots++;
            final int occurrenceRate = 3;
            SoundEffect.SHOOT.playSometimes(occurrenceRate);

            shootBullet();
        }

        canShoot = !shooting;
    }

    private void shootBullet() {
        Constructor<T> constructor;

        try {
            constructor = bulletType.getConstructor(
                    Vec2d.class, Character.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return;
        }

        T bullet;

        try {
            bullet = constructor.newInstance(getPosition(), character);
        } catch (IllegalAccessException
                | InstantiationException
                | InvocationTargetException e) {
            e.printStackTrace();
            return;
        }

        getLevel().addEntity(bullet);
    }

    /**
     * Toggles whether the character should be shooting.
     *
     * @param shooting Boolean - indicates whether the character
     *                 is shooting or not.
     */
    public void setShooting(final boolean shooting) {
        this.shooting = shooting;
    }

    /**
     * Increases the amount of bullets this character can shoot.
     *
     * @param amount Integer - the amount of bullets a character
     *               can shoot extra.
     */
    public void increaseMaxConcurrentShots(final int amount) {
        maxConcurrentShots = Math.max(1, maxConcurrentShots + amount);
    }

    /**
     * Called when a bullet dies.
     */
    public void bulletDied() {
        currentConcurrentShots = Math.max(0, currentConcurrentShots - 1);
    }

    /**
     * @return The amount of shots this gun can shoot concurrently.
     */
    public int getMaxConcurrentShots() {
        return maxConcurrentShots;
    }

}
