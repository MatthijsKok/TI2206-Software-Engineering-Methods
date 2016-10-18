package entities.powerups;
import game.Game;
import graphics.Sprite;
import level.Level;
import util.SoundManager;

/**
 * Power-up that adds extra time to the level time.
 */
class ExtraTime extends AbstractInstantPowerUp {

    /**
     * The amount of time in seconds that is added on picking up this powerup.
     */
    private static final double EXTRA_TIME = 10;

    /**
     * The sprite of the extra time power-up.
     */
    private static final Sprite EXTRA_TIME_SPRITE = new Sprite("powerUps/extra_time.png");

    /**
     * The name of the sound effect that will be played when the power-up is picked up.
     */
    private static final String SOUND_EFFECT_NAME = "extra_time.wav";

    /**
     * Creates a new ExtraTime power-up.
     */
    ExtraTime() {
        setSprite(EXTRA_TIME_SPRITE);
    }

    private Level getLevel() {
        return Game.getInstance().getState().getCurrentLevel();
    }

    @Override
    void applyEffect() {
        Level level = getLevel();
        level.increaseTime(EXTRA_TIME);
        SoundManager.playSoundEffect(SOUND_EFFECT_NAME);
    }
}
