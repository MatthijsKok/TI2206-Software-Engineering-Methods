package entities.powerups;

import graphics.Sprite;
import util.sound.SoundEffect;

/**
 * Gives a character an extra vine to shoot.
 */
class ExtraVine extends AbstractDuringPowerUp {

    /**
     * The sprite of the extra vine power-up.
     */
    private static final Sprite SPRITE = new Sprite("powerUps/extra_vine.png");
    /**
     * The duration of the extra vine power-up (in ms).
     */
    private static final long DURATION = 10000;

    /**
     * Creates a new extra vine power-up.
     */
    ExtraVine() {
        super();
        setSprite(SPRITE);
        setDuration(DURATION);
    }

    /**
     * Increase the amount of vines the character can shoot.
     */
    protected final void enableEffect() {
        getTarget().getGun().increaseMaxConcurrentShots(1);
        SoundEffect.EXTRA_VINE.play();
    }

    /**
     * Disables the effect of the specific power up.
     */
    protected final void disableEffect() {
        getTarget().getGun().increaseMaxConcurrentShots(-1);
    }

}
