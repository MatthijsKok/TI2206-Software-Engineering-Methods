package entities.powerups;

import entities.Character;

/**
 * Gives the player a shield that allows it to be hit one time.
 */
public class Shield implements PowerUp {

    /**
     * Enables the effect of the specific power up.
     */
    @Override
    public void enableEffect(Character character) {
        System.out.println("Shield");
    }

    /**
     * Disables the effect of the specific power up.
     */
    @Override
    public void disableEffect(Character character) {

    }

}
