package entities.powerups;
import game.Game;
import graphics.Sprite;
import util.sound.Music;
import util.sound.SoundEffect;

/**
 * Power-up that adds extra time to the level time.
 */
class ExtraTime extends AbstractInstantPowerUp {

    /**
     * The amount of time in seconds that is added on picking up this power-up.
     */
    private static final double EXTRA_TIME = 5;
    /**
     * The sprite of the extra time power-up.
     */
    private static final Sprite EXTRA_TIME_SPRITE = new Sprite("powerUps/extra_time.png");

    /**
     * Creates a new ExtraTime power-up.
     */
    ExtraTime() {
        setSprite(EXTRA_TIME_SPRITE);
    }

    @Override
    /* default */ final void applyEffect() {
        Game.getCurrentLevel().getTimer().increaseTime(EXTRA_TIME);
        SoundEffect.EXTRA_TIME.play();
        SoundEffect.TIME_ALMOST_UP.getAudio().stop();
        Music.startMusic();
    }
}
