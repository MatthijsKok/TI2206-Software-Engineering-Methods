package entities.powerups;

import entities.Character;

/**
 * Gives the player a extra rope to shoot.
 */
public class ExtraRope implements PowerUp {

    /**
     * Enables the effect of the specific power up.
     */
    @Override
    public void enableEffect(Character character) {
        System.out.println("Extra rope");
    }

    /**
     * Disables the effect of the specific power up.
     */
    @Override
    public void disableEffect(Character character) {

    }

}
