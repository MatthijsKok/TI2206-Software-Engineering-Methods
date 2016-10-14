package entities.powerups;
import entities.Character;

/**
 * Adds a life to the players total.
 */
public class ExtraLife implements PowerUp {

    /**
     * Enables the effect of the specific power up.
     */
    @Override
    public void enableEffect(Character character) {
        System.out.println("Extra life");
        character.getPlayer().setLives(character.getPlayer().getLives() + 1);
    }

    /**
     * Disables the effect of the specific power up.
     */
    @Override
    public void disableEffect(Character character) {

    }

}
