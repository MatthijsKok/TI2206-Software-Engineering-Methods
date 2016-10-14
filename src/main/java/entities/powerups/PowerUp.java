package entities.powerups;
import game.player.Player;

public interface PowerUp {

    /**
     * Character of the game
     */
    Character character = null;

    /**
     * Player of the game
     */
    Player player = null;

    /**
     * Enables the effect of the specific power up.
     */
    public void enableEffect();

    /**
     * Disables the effect of the specific power up.
     */
    public void disableEffect();

}
