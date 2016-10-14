package entities.powerups;
import entities.Character;
import game.Game;
import game.player.Player;

public class ExtraLife implements PowerUp {

    /**
     * Character of the game.
     */
    Character character;

    /**
     * Player of the game.
     */
    Player player;

    /**
     * Enables the effect of the specific power up.
     */
    @Override
    public void enableEffect() {
        player.setLives(player.getLives() + 1);
    }

    /**
     * Disables the effect of the specific power up.
     */
    @Override
    public void disableEffect() {

    }

}
