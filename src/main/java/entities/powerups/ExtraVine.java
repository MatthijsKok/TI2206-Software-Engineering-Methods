package entities.powerups;

import graphics.Sprite;
import util.sound.MultiSoundEffect;

/**
 * Gives the images.player a extra vine to shoot.
 */
class ExtraVine extends AbstractDuringPowerUp {

    /**
     * The sprite of the extra vine power-up.
     */
    private static final Sprite SPRITE = new Sprite("images/powerUps/extra_vine.png");

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
    /* default */ void enableEffect() {
        getTarget().increaseMaxVineCount(1);
        MultiSoundEffect.EXTRA_VINE.play(getTarget().getPlayer().getId());
    }

    /**
     * Disables the effect of the specific power up.
     */
    /* default */ void disableEffect() {
        getTarget().increaseMaxVineCount(-1);
    }

}
